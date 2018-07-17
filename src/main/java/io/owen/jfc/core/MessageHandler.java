package io.owen.jfc.core;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import io.owen.jfc.entity.User;
import io.owen.jfc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by owen_q on 2018. 7. 10..
 */

@Component
public class MessageHandler {
    private Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private UserRepository userRepository;

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

        JsonNode handleResult = null;

        // check auth
        if(isAuthoredUser(userKey)){
            // change user input 'content' to next command
            Optional<UserState> optionalNextUserState = stateList.find(content);


            handleResult = optionalNextUserState.map(nextUserState -> {
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
        else{
            // unAuthored User
            // TODO: Refactoring
            List<String> mainCommandList = stateList.getMainCommands();

            JsonNode messageNode = responseFactory.createMessageNode("인증 후 이용해주세요", null);
            JsonNode keyboardNode = responseFactory.createButtonsKeyboardNode(mainCommandList);

            handleResult = responseFactory.createResult(messageNode, keyboardNode);
        }

        return handleResult;
    }

    private boolean isAuthoredUser(String userKey){
        User userInfo = userRepository.findByUserKey(userKey);
        return userInfo.isAuthored();
    }

    public JsonNode generateCommands(){
        return null;
    }
}
