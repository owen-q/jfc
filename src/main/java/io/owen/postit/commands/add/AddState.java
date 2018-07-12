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
@Command(name = "추가하기", state = UserState.ADD, availableNextState = {UserState.ADD_ENTER})
public class AddState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(AddState.class);

    @Override
    public JsonNode handle(String userKey, Map<String, Object> attrs) {
        return null;
    }

    @Override
    public JsonNode generateResponse() {
        return null;
    }
}
