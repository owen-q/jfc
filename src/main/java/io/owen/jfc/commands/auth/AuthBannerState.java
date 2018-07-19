package io.owen.jfc.commands.auth;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.commands.Command;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import io.owen.jfc.common.entity.User;
import io.owen.jfc.common.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by owen_q on 2018. 7. 15..
 */
@Command(state = UserState.AUTH_BANNER, availableNextState = {UserState.AUTH_INPUT, UserState.HOME})
public class AuthBannerState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(AuthBannerState.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public JsonNode handle(String userKey, Map<String, Object> attrs) {
        User existUser = userRepository.findByUserKey(userKey);

        // TODO: Constants
        String enteredUserName = (String) attrs.get("userName");
        existUser.setUserName(enteredUserName);
        User savedUser = userRepository.save(existUser);

        if(savedUser.getUserName().equals(enteredUserName)){
            // success

        }
        else{

        }



        // handle auth
        return result;


    }

    @Override
    public JsonNode printOptions(String userKey, Map<String, Object> attrs) {
        return generateResponse();
    }

    @Override
    public JsonNode generateResponse() {
        JsonNode messageNode = responseFactory.createMessageNode("이름을 입력해주세요", null);
        JsonNode keyboardNode = responseFactory.createTextKeyboardNode();

        JsonNode result = responseFactory.createResult(messageNode, keyboardNode);

        return result;
    }
}
