package io.owen.jfc.commands.auth;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.commands.Command;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by owen_q on 2018. 7. 15..
 */
@Command(state = UserState.AUTH_BANNER, availableNextState = UserState.AUTH_INPUT)
public class AuthBannerState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(AuthBannerState.class);

    @Override
    public JsonNode handle(String userKey, Map<String, Object> attrs) {
        //Do somerthing...
        JsonNode result = generateResponse();

        return result;
    }

    @Override
    public JsonNode generateResponse() {

        /*
        JsonNode messageNode = responseFactory.createMessageNode("이름을 입력해주세요", null);
        JsonNode messageButtonNode = responseFactory.createButtonsKeyboardNode();
        JsonNode messageNode = responseFactory.createMessageNode("hi", messageButtonNode);
        result = responseFactory.createResult(messageNode);
        */

        return null;
    }
}
