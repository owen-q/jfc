package io.owen.jfc.commands;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.core.ResponseFactory;
import io.owen.jfc.core.StateManager;

import java.util.Map;

/**
 * Created by owen_q on 2018. 7. 10..
 */
public interface CommandHandler {
    StateManager stateManager = StateManager.getInstance();
    ResponseFactory responseFactory = ResponseFactory.getInstance();

//    boolean isValidAction(UserState nextUserState);



    JsonNode handle(String userKey, Map<String, Object> attrs);

    /**
     * User Content가 command일때, 해당 command의 옵션들을 보여준다
     * @param userKey
     * @param attrs
     * @return
     */
    JsonNode printOptions(String userKey, Map<String, Object> attrs);
    JsonNode generateResponse();

    default boolean isValidAction(UserState nextUserState){
        System.out.println(this.getClass().getName());
        return true;
    }

    default void changeState(String userKey, UserState newUserState){
        stateManager.change(userKey, newUserState);
    }
}
