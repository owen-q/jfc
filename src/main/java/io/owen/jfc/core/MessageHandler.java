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

    @Autowired
    private StateManager stateManager;

    @Autowired
    private StateList stateList;

    public MessageHandler() {
    }

    public Response handle(ServerRequest serverRequest, JsonNode jsonRequestBody){
        if(logger.isInfoEnabled())
            logger.info(jsonRequestBody.toString());

        final String userKey = jsonRequestBody.get("user_key").asText();
        final String type = jsonRequestBody.get("type").asText();
        final String content = jsonRequestBody.get("content").asText();

        UserState currentUserState = stateManager.get(userKey);

        stateList.getAvailableStateSet().stream().forEach(state->logger.info(state.toString()));

        // change user input 'content' to next command
        Optional<UserState> optionalNextUserState = stateList.find(content);

        Response response = null;

        response = optionalNextUserState.map(nextUserState -> {
            logger.info("In nextUserState map");

            // content is {@link UserState}
            Response result = null;

            if(isAuthoredUser(userKey) || nextUserState == UserState.AUTH_BANNER){
                CommandHandler expectedCommandHandler = stateList.getCommandHandler(nextUserState.getValue());
                Map<String, Object> attrs = new HashMap<>();

                result = expectedCommandHandler.handleCommand(userKey, null);

                stateManager.change(userKey, nextUserState);
            }
            else{
                if(!isExistUser(userKey)){
                    User existedUser = new User(userKey);
                    userRepository.saveAndFlush(existedUser);
                }

                // unAuthored user
                List<String> mainCommandList = stateList.getMainCommands();

                result = new ResponseBuilder()
                        .keyboardType(KeyboardType.BUTTONS)
                        .message("인증 후 이용해주세요")
                        .buttons(mainCommandList)
                        .build();
            }

            return result;
        }).orElseGet(()->{
            logger.info("In nextUserState elseGet");
            // content is user input
            // TODO: exceptional case 처리->Home 이동
            Response result = null;
            try{
                Map<String, Object> attrs = new HashMap<>();
                attrs.put("content", content);

                CommandHandler expectedCommandHandler = stateList.getCommandHandler(currentUserState.getValue());
                result = expectedCommandHandler.handleInput(userKey, attrs);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            if(result == null){
                List<String> commands = stateList.getMainCommands();
                result = new ResponseBuilder().keyboardType(KeyboardType.BUTTONS).message("오류가 발생했습니다. 처음부터 다시 시도해주세요 ㅠㅠ").buttons(commands).build();
            }

            return result;
        });

        return response;
    }

    private boolean isExistUser(String userKey){
        User userInfo = userRepository.findByUserKey(userKey);

        if(userInfo == null)
            return false;
        else
            return true;
    }

    private boolean isAuthoredUser(String userKey){
        User userInfo = userRepository.findByUserKey(userKey);

        if(userInfo == null)
            return false;

        return userInfo.isAuthored();
    }

    public JsonNode generateCommands(){
        return null;
    }
}
