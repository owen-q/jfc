package io.owen.jfc.core;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import io.owen.jfc.common.entity.User;
import io.owen.jfc.common.repository.UserRepository;
import io.owen.jfc.model.KeyboardType;
import io.owen.jfc.model.Response;
import io.owen.jfc.model.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Response handle(ServerRequest serverRequest, JsonNode jsonRequestBody){
        if(logger.isInfoEnabled())
            logger.info(jsonRequestBody.toString());

        final String userKey = jsonRequestBody.get("user_key").asText();
        final String type = jsonRequestBody.get("type").asText();
        final String content = jsonRequestBody.get("content").asText();

        UserState currentUserState = stateManager.get(userKey);
        Optional<UserState> optionalNextUserState = stateList.find(content);

        Response response = null;

        // change user input 'content' to next command
        response = optionalNextUserState.map(nextUserState -> {
            Response result = null;

            if(isAuthoredUser(userKey) || nextUserState == UserState.AUTH_BANNER){
                CommandHandler expectedCommandHandler = stateList.getCommandHandler(nextUserState.getValue());
                Map<String, Object> attrs = new HashMap<>();

                result = expectedCommandHandler.printOptions(userKey, null);

                stateManager.change(userKey, nextUserState);
            }
            else{
                // unAuthored user
                List<String> mainCommandList = stateList.getMainCommands();

                JsonNode messageNode = responseFactory.createMessageNode("인증 후 이용해주세요", null);
                JsonNode keyboardNode = responseFactory.createButtonsKeyboardNode(mainCommandList);

//                result = responseFactory.createResult(messageNode, keyboardNode);

                result = new ResponseBuilder()
                        .keyboardType(KeyboardType.BUTTONS)
                        .message("인증 후 이용해주세요")
                        .buttons(mainCommandList)
                        .build();
            }

            return result;
        }).orElseGet(()->{
            // content is user input
            // TODO: exceptional case 처리->Home 이동
            CommandHandler expectedCommandHandler = stateList.getCommandHandler(currentUserState.getValue());
            Response result = null;

            result = expectedCommandHandler.handle(userKey, null);

            return result;
        });

        return response;
    }

    private boolean isAuthoredUser(String userKey){
        User userInfo = userRepository.findByUserKey(userKey);
        return userInfo.isAuthored();
    }

    public JsonNode generateCommands(){
        return null;
    }
}
