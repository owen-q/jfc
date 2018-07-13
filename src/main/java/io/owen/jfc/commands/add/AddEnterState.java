package io.owen.jfc.commands.add;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.commands.Command;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by owen_q on 2018. 7. 11..
 */
@Command(name = "입력하기", state = UserState.ADD_ENTER, availableNextState = {UserState.ADD_CONFIRM})
public class AddEnterState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(AddEnterState.class);

    @Override
    public JsonNode handle(String userKey, Map<String, Object> attrs) {
        return null;
    }

    @Override
    public JsonNode generateResponse() {
        return null;
    }

    @Override
    public boolean isValidAction(UserState nextUserState) {
        return false;
    }

    @Override
    public void changeState(String userKey, UserState newUserState) {

    }
}
