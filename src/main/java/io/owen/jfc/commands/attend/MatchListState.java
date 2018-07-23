package io.owen.jfc.commands.attend;

import io.owen.jfc.commands.Command;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import io.owen.jfc.common.entity.Match;
import io.owen.jfc.common.entity.User;
import io.owen.jfc.common.repository.MatchRepository;
import io.owen.jfc.common.repository.UserRepository;
import io.owen.jfc.model.KeyboardType;
import io.owen.jfc.model.Response;
import io.owen.jfc.model.ResponseBuilder;
import io.owen.jfc.util.Cache;
import io.owen.jfc.util.WeekConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by owen_q on 2018. 7. 17..
 */
@Component
@Command(state = UserState.MATCH_LIST, availableNextState = {UserState.MATCH_ATTEND, UserState.MATCH_NONATTEND, UserState.HOME})
public class MatchListState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(MatchListState.class);

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cache cache;

    public MatchListState() {

    }

    @Override
    public Response handle(String userKey, Map<String, Object> attrs) {
        String content = (String) attrs.get("content");
        String matchDate = content.split("\n")[0].split("> ")[1];

        LocalDate localMathDate = LocalDate.parse(matchDate);

        Optional<Match> maybeTargetMatchInfo = matchRepository.findById(localMathDate);

        // Store match
        cache.set(userKey, maybeTargetMatchInfo);

        StringBuilder resultMessageBuilder = new StringBuilder();
        resultMessageBuilder.append("> "  + matchDate + "\n");
        resultMessageBuilder.append("참석자 현황:\n\n");


        Response response = null;

        response = maybeTargetMatchInfo.map(targetMatchInfo -> {
            List<User> attendList = targetMatchInfo.getAttendList();

            resultMessageBuilder.append("[참석]\n");

            attendList.forEach(attendUser -> {
                resultMessageBuilder.append(attendUser.getUserName() + "\n");
            });


            List<User> nonAttendList = targetMatchInfo.getNonAttendList();


            resultMessageBuilder.append("[불참]\n");
            nonAttendList.forEach(nonAttendUser ->{
                resultMessageBuilder.append(nonAttendUser.getUserName() + "\n");
            });

            Response successResponse = new ResponseBuilder()
                    .keyboardType(KeyboardType.BUTTONS)
                    .message(resultMessageBuilder.toString())
                    .buttons(getNextStates().stream().map(UserState::getValue).collect(Collectors.toList()))
                    .build();

            return successResponse;
        }).orElseGet(()->{
            Response exceptionResponse = new ResponseBuilder()
                    .keyboardType(KeyboardType.BUTTONS)
                    .message(resultMessageBuilder.toString())
                    .buttons(getNextStates().stream().map(UserState::getValue).collect(Collectors.toList()))
                    .build();

            return exceptionResponse;
        });

        return response;
    }

    @Override
    public Response printOptions(String userKey, Map<String, Object> attrs) {
        User user = userRepository.findByUserKey(userKey);
        String userName = user.getUserName();

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        List<Match> availableMatchList = matchRepository.findByMatchDateAfter(now.toLocalDate());
        StringBuilder responseMessageBuilder = new StringBuilder();
        List<String> commands = new ArrayList<>();

        String menuItem = "> %s(%s) 참석:%d, 불참:%d";

        availableMatchList.stream().forEach(match->{
            commands.add(String.format(menuItem, match.getMatchDate().format(DateTimeFormatter.ISO_DATE), WeekConverter.convert(match.getMatchDate().getDayOfWeek()), match.getAttendList().size(), match.getNonAttendList().size()) );
        });

        commands.add(UserState.HOME.getValue());

        responseMessageBuilder.append("안녕하세요! " + userName + "\n");
        responseMessageBuilder.append("현재 참석 가능한 경기는 총 " + availableMatchList.size() + "개 입니다.\n");
        responseMessageBuilder.append(StringUtils.repeat("-", responseMessageBuilder.length() + 5));

        Response result = new ResponseBuilder().keyboardType(KeyboardType.BUTTONS).buttons(commands).message(responseMessageBuilder.toString()).build();

        return result;
    }

    @Override
    public Response generateResponse() {
        return null;
    }
}
