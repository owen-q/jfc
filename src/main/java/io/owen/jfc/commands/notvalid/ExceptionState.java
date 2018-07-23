package io.owen.jfc.commands.notvalid;

import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import io.owen.jfc.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by owen_q on 2018. 7. 13..
 */
//@Command(name = "아직 잘 모르겠어요", state = )
public class ExceptionState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(ExceptionState.class);

    @Override
    public boolean isValidAction(UserState nextUserState) {
        return false;
    }

    @Override
    public Response handleCommand(String userKey, Map<String, Object> attrs) {


        return null;
    }

    @Override
    public Response handleInput(String userKey, Map<String, Object> attrs) {
        return null;
    }

    @Override
    public Response generateResponse() {
        return null;
    }
}
