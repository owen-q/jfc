package io.owen.jfc.commands.home;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.commands.Command;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by owen_q on 2018. 7. 16..
 */
@Command(state = UserState.HOME, availableNextState = {UserState.AUTH_BANNER, UserState.MATCH_ATTEND})
public class HomeState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(HomeState.class);

    @Override
    public JsonNode handle(String userKey, Map<String, Object> attrs) {

        generateResponse();
        return null;
    }

    @Override
    public JsonNode generateResponse() {
        Command command = this.getClass().getDeclaredAnnotation(Command.class);

        UserState[] availableStates = command.availableNextState();

        JsonNode messageNode = responseFactory.createMessageNode("메인화면", null);

        String[] stateNameArr = (String[]) Stream.of(availableStates).map(userState -> userState.getValue()).toArray();
        JsonNode keyboardNode = responseFactory.createButtonsKeyboardNode(stateNameArr);

        JsonNode result = responseFactory.createResult(messageNode, keyboardNode);

        return result;
    }
}

