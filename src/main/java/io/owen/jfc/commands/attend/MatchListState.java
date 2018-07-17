package io.owen.jfc.commands.attend;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.commands.Command;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by owen_q on 2018. 7. 17..
 */
@Command(state = UserState.MATCH_LIST, availableNextState = {UserState.MATCH_ATTEND, UserState.MATCH_NONATTEND, UserState.HOME})
public class MatchListState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(MatchListState.class);

    @Override
    public JsonNode handle(String userKey, Map<String, Object> attrs) {

        return null;
    }

    @Override
    public JsonNode printOptions(String userKey, Map<String, Object> attrs) {
        /*
        Command command = this.getClass().getDeclaredAnnotation(Command.class);

        UserState[] availableStates = command.availableNextState();
        List<String> availableStringArr = Stream.of(availableStates).map(availableState -> availableState.getValue()).collect(Collectors.toList());

        JsonNode keyboardNode = responseFactory.createButtonsKeyboardNode(availableStringArr);
        JsonNode messageNode = responseFactory.createMessageNode("이번주", );


        JsonNode result = responseFactory.createResult(messageNode, keyboardNode);
        */

        JsonNode result = null;

        return result;
    }

    @Override
    public JsonNode generateResponse() {
        return null;
    }
}
