package io.owen.jfc.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.owen.jfc.commands.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by owen_q on 2018. 7. 10..
 */

@Component
public class MessageHandler {
    private Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    @Autowired
    private ResponseFactory responseFactory;

    private StateManager stateManager;

    private StateList stateList;

    private String endpointUrl = "https://illius.serveo.net";

    public MessageHandler() {
        this.stateManager = StateManager.getInstance();
        this.stateList = StateList.getInstance();
    }

    public JsonNode handle(JsonNode jsonRequestBody){
        if(logger.isInfoEnabled())
            logger.info(jsonRequestBody.toString());

        String userKey = jsonRequestBody.get("user_key").asText();
        String type = jsonRequestBody.get("type").asText();
        String content = jsonRequestBody.get("content").asText();

        UserState currentUserState = stateManager.get(userKey);

        // change user input 'content' to next command
        Optional<UserState> optionalNextUserState = stateList.find(content);

        /*
        return optionalNextUserState.map(nextUserState -> {
            JsonNode result = null;
            CommandHandler currentCommandHandler = stateList.getCommandHandler(currentUserState.name());

            if(currentCommandHandler.isValidAction(nextUserState)){
                // handle expected scenario

                currentCommandHandler.handle(userKey, null);
            }
            else{
                // handle exceptional case
                // Go to main hierachy & set state

                // Generate respoonse
                stateManager.reset(userKey);
            }

            JsonNode jsonNode = responseFactory.createObjectNode("type", "buttons");
            JsonNode arrayNode = responseFactory.createArrayNode();

            ((ArrayNode) arrayNode).add("1");
            ((ArrayNode) arrayNode).add("2");
            ((ArrayNode) arrayNode).add("3");

            JsonNode messageButtonNode = responseFactory.createButtonsKeyboard();
            JsonNode messageNode = responseFactory.createMessage("hi", messageButtonNode);
            result = responseFactory.createResult(messageNode);

            return result;
        });
        */

        JsonNode jsonNode = responseFactory.createObjectNode("type", "buttons");
        JsonNode arrayNode = responseFactory.createArrayNode();
        JsonNode result = null;

        ((ArrayNode) arrayNode).add("1");
        ((ArrayNode) arrayNode).add("2");
        ((ArrayNode) arrayNode).add("3");

        JsonNode messageButtonNode = responseFactory.createButtonsKeyboard();
        JsonNode messageNode = responseFactory.createMessage("hi", messageButtonNode);
//        result = responseFactory.createResult(messageNode);
        return messageNode;
    }

    public JsonNode generateCommands(){
        return null;
    }
}
