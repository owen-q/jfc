package io.owen.jfc.commands.attend;

import io.owen.jfc.commands.Command;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import io.owen.jfc.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by owen_q on 2018. 7. 20..
 */

@Command(state = UserState.MATCH_ATTEND, availableNextState = {UserState.HOME})
public class MatchAttendState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(MatchAttendState.class);


    @Override
    public Response handle(String userKey, Map<String, Object> attrs) {
        return null;
    }

    @Override
    public Response printOptions(String userKey, Map<String, Object> attrs) {
        return null;
    }

    @Override
    public Response generateResponse() {
        return null;
    }
}
