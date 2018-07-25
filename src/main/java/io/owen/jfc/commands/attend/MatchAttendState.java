package io.owen.jfc.commands.attend;

import io.owen.jfc.commands.Command;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import io.owen.jfc.common.entity.Match;
import io.owen.jfc.common.entity.User;
import io.owen.jfc.common.repository.MatchRepository;
import io.owen.jfc.common.repository.UserRepository;
import io.owen.jfc.core.StateList;
import io.owen.jfc.model.KeyboardType;
import io.owen.jfc.model.Response;
import io.owen.jfc.model.ResponseBuilder;
import io.owen.jfc.util.Cache;
import io.owen.jfc.util.WeekConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

/**
 * Created by owen_q on 2018. 7. 20..
 */

@Component
@Command(state = UserState.MATCH_ATTEND, availableNextState = {UserState.HOME, UserState.AUTH_BANNER})
public class MatchAttendState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(MatchAttendState.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private StateList stateList;

    @Autowired
    private Cache cache;

    @Override
    public Response handleInput(String userKey, Map<String, Object> attrs) {
        return null;
    }

    @Override
    public Response handleCommand(String userKey, Map<String, Object> attrs) {
        if(logger.isInfoEnabled())
            logger.info("{} handle", UserState.MATCH_ATTEND.getValue());

        LocalDate matchDate = (LocalDate) cache.get(userKey);
        Optional<Match> maybeMatch = matchRepository.findById(matchDate);
        Response response = null;

        response = maybeMatch
                .map((match)->{
                    User attendUser = userRepository.findByUserKey(userKey);

                    match.getAttendList().add(attendUser);

                    Match savedMatch = matchRepository.saveAndFlush(match);

                    /*
                    // TODO
                    if(savedMatch.getId() == match.getId())
                        // success
                        ;
                    else
                        // Fail
                    ;
                    */

                    StringBuilder resultMessageBuilder = new StringBuilder();

                    String messageFormat = "> %s (%s) 경기에 %s되었습니다.";
                    resultMessageBuilder.append(String.format(messageFormat, match.getMatchDate().format(DateTimeFormatter.ISO_DATE), WeekConverter.convert(match.getMatchDate().getDayOfWeek()), UserState.MATCH_ATTEND.getValue()));

                    Response successResponse = new ResponseBuilder()
                            .keyboardType(KeyboardType.BUTTONS)
                            .message(resultMessageBuilder.toString())
                            .buttons(stateList.getMainCommands())
                            .build();

                    return successResponse;
                })
                .orElseGet(()->{
                    Response failResponse = new ResponseBuilder()
                            .keyboardType(KeyboardType.BUTTONS)
                            .buttons(stateList.getMainCommands())
                            .message("오류가 발생했습니다...")
                            .build();

                    return failResponse;
                });

        return response;
    }

    @Override
    public Response generateResponse() {
        return null;
    }
}
