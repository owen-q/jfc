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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public Response handle(String userKey, Map<String, Object> attrs) {

        return null;
    }

    @Override
    public Response printOptions(String userKey, Map<String, Object> attrs) {
        User user = userRepository.findByUserKey(userKey);
        String userName = user.getUserName();

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        List<Match> availableMatchList = matchRepository.findByMatchDateAfter(now.toLocalDate());
        StringBuilder responseMessageBuilder = new StringBuilder();
        List<String> commands = new ArrayList<>();

        String menuItem = "%s\n참석:%d, 불참:%d";

        availableMatchList.stream().forEach(match->{
            commands.add(String.format(menuItem, match.getMatchDate().format(DateTimeFormatter.ISO_DATE), match.getAttendList().size(), match.getNonAttendList().size()) );
        });

        commands.add(UserState.HOME.getValue());

        responseMessageBuilder.append("안녕하세요 " + userName + "\n");
        responseMessageBuilder.append("현재 참석 가능한 경기는 총 " + availableMatchList.size() + "개 입니다.\n");
        responseMessageBuilder.append("-----------------------------------------");

        Response result = new ResponseBuilder().keyboardType(KeyboardType.BUTTONS).buttons(commands).message(responseMessageBuilder.toString()).build();

        return result;
    }

    @Override
    public Response generateResponse() {
        return null;
    }
}
