package io.owen.postit.commands;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.postit.core.StateManager;

import java.util.Map;

/**
 * Created by owen_q on 2018. 7. 10..
 */
public interface CommandHandler {
    StateManager stateManager = StateManager.getInstance();


    boolean isValidAction(UserState nextUserState);
    JsonNode handle(String userKey, Map<String, Object> attrs);
    JsonNode generateResponse();

    default void changeState(String userKey, UserState newUserState){
        stateManager.change(userKey, newUserState);
    }
}
