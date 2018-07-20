package io.owen.jfc.commands.auth;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.commands.Command;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import io.owen.jfc.common.entity.User;
import io.owen.jfc.common.repository.UserRepository;
import io.owen.jfc.core.StateList;
import io.owen.jfc.model.KeyboardType;
import io.owen.jfc.model.Response;
import io.owen.jfc.model.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by owen_q on 2018. 7. 15..
 */
@Component
@Command(state = UserState.AUTH_BANNER, availableNextState = {UserState.AUTH_INPUT, UserState.HOME})
public class AuthBannerState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(AuthBannerState.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response handle(String userKey, Map<String, Object> attrs) {
        Response response = null;
        User existUser = userRepository.getOne(userKey);

        // TODO: Constants
        String enteredUserName = (String) attrs.get("content");
        existUser.setUserName(enteredUserName);
        existUser.setAuthored(true);

        User savedUser = userRepository.save(existUser);
        List<String> commands = null;

        commands = StateList.getInstance().getMainCommands();

        if(savedUser.getUserName().equals(enteredUserName)){
            // success
            response = new ResponseBuilder().keyboardType(KeyboardType.BUTTONS).message("인증성공").buttons(commands).build();
        }
        else{
            response = new ResponseBuilder().keyboardType(KeyboardType.TEXT).message("인증실패").buttons(commands).build();
        }

        return response;
    }

    @Override
    public Response printOptions(String userKey, Map<String, Object> attrs) {
        // state change
//        stateManager.change(userKey, UserState.AUTH_BANNER);

        return generateResponse();
    }

    @Override
    public Response generateResponse() {
        JsonNode messageNode = responseFactory.createMessageNode("이름을 입력해주세요", null);
        JsonNode keyboardNode = responseFactory.createTextKeyboardNode();

        Response response = null;
        response = new ResponseBuilder().keyboardType(KeyboardType.TEXT).message("이름을 입력해주세요").build();

        return response;
    }
}
