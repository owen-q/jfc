package io.owen.jfc.core;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.commands.CommandHandler;
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

        // check auth . . . . ?

        return optionalNextUserState.map(nextUserState -> {
            CommandHandler expectedCommandHandler = stateList.getCommandHandler(nextUserState.getValue());
            JsonNode result = null;

            result = expectedCommandHandler.printOptions(userKey, null);

            stateManager.change(userKey, nextUserState);

            return result;
        }).orElseGet(()->{
            // content is user input
            CommandHandler expectedCommandHandler = stateList.getCommandHandler(currentUserState.getValue());
            JsonNode result = null;

            result = expectedCommandHandler.handle(userKey, null);

            return result;
        });
    }

    public JsonNode generateCommands(){
        return null;
    }
}
