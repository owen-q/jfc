package io.owen.postit.commands.add;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.postit.commands.Command;
import io.owen.postit.commands.CommandHandler;
import io.owen.postit.commands.UserState;
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
}
