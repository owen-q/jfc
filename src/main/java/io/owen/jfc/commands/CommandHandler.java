package io.owen.jfc.commands;

import io.owen.jfc.core.ResponseFactory;
import io.owen.jfc.core.StateManager;
import io.owen.jfc.model.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by owen_q on 2018. 7. 10..
 */
public interface CommandHandler {
    StateManager stateManager = StateManager.getInstance();
    ResponseFactory responseFactory = ResponseFactory.getInstance();



    Response handleInput(String userKey, Map<String, Object> attrs);

    /**
     * User Content가 command일때, 해당 command의 옵션들을 보여준다
     * @param userKey
     * @param attrs
     * @return
     */
    Response handleCommand(String userKey, Map<String, Object> attrs);
    Response generateResponse();

    default boolean isValidAction(UserState nextUserState){
        System.out.println(this.getClass().getName());
        return true;
    }

    default List<UserState> getNextStates(){
        Command command = this.getClass().getDeclaredAnnotation(Command.class);

        return Arrays.asList(command.availableNextState());
    }

    default void changeState(String userKey, UserState newUserState){
        stateManager.change(userKey, newUserState);
    }

    String a = "";

    static void asdf(){

    }
}
