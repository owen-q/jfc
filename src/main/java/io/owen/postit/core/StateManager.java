package io.owen.postit.core;

import io.owen.postit.commands.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by owen_q on 2018. 7. 10..
 */
public class StateManager {
    private Logger logger = LoggerFactory.getLogger(StateManager.class);

    private final int INITIALI_CAPACITY = 10000;

    private Map<String, UserState> userStateMap;

    public static StateManager getInstance() {
        return Holder.INSTANCE;
    }

    public StateManager() {
        this.userStateMap = new ConcurrentHashMap<>(INITIALI_CAPACITY);
    }

    public void change(String userKey, UserState newState){
        userStateMap.put(userKey, newState);
    }

    public UserState get(String userKey){
        UserState currentState = userStateMap.get(userKey);
        if(currentState == null)
            currentState = UserState.HOME;

        return currentState;
    }

    public void reset(String userKey){
        userStateMap.put(userKey, UserState.HOME);
    }

    private static class Holder{
        private static StateManager INSTANCE = new StateManager();
    }
}
