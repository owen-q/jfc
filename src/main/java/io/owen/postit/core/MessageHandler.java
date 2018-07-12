package io.owen.postit.core;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.postit.commands.CommandHandler;
import io.owen.postit.commands.UserState;
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

        /*
        JsonNode messageButton = responseFactory.createMessageButtonNode("list", endpointUrl);
        JsonNode message = responseFactory.createMessage("안녕하세요", messageButton);
        JsonNode result = responseFactory.createResult(message);
        */

        UserState currentUserState = stateManager.get(userKey);

        // change user input 'content' to next command
        Optional<UserState>  optionalNextUserState = stateList.find(content);

        optionalNextUserState.map(nextUserState -> {
            CommandHandler currentCommandHandler = stateList.getCommandHandler(currentUserState.name());

            if(currentCommandHandler.isValidAction(nextUserState)){
                // handle expected scenario
                currentCommandHandler.handle(userKey, )
            }
            else{
                // handle exceptional case
                // Go to main hierachy & set state

                // Generate respoonse
                stateManager.reset(userKey);
            }
        }).

        return result;
    }

    public JsonNode generateCommands(){
        return null;
    }
}
