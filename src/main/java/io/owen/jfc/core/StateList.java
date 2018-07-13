package io.owen.jfc.core;

import io.owen.jfc.commands.Command;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by owen_q on 2018. 7. 11..
 */
public class StateList {
    private Logger logger = LoggerFactory.getLogger(StateList.class);

    private Map<String, CommandHandler> commandHandlerMap;
    private List<UserState> availableStateSet;

    public static StateList getInstance() {
        return Holder.INSTANCE;
    }

    private StateList() {
        commandHandlerMap = new HashMap<>();
        this.availableStateSet = new ArrayList<>();

        // Parse annotations
        Reflections reflections = new Reflections("io.owen.jfc");
        Set<Class<?>> commands = reflections.getTypesAnnotatedWith(Command.class);

        try {
            String commandName;
            UserState currentState;
            UserState[] availableState;
            CommandHandler handler;

            for(Class commandClass : commands) {
                Command commandAnnotation = (Command) commandClass.getDeclaredAnnotation(Command.class);

                commandName = commandAnnotation.name();
                currentState = commandAnnotation.state();

                handler = (CommandHandler) commandClass.getConstructor().newInstance();

                commandHandlerMap.put(commandName, handler);
                availableStateSet.add(currentState);
            }
        }
        catch (Exception e){
            e.printStackTrace();

        }
        System.out.println("break");

    }

    public Optional<UserState> find(String userEnteredContent){
        return availableStateSet.stream().filter(actualUserState -> actualUserState.getValue().equals(userEnteredContent)).findAny();
    }

    public CommandHandler getCommandHandler(String stateName){
        return this.commandHandlerMap.get(stateName);
    }

    private static class Holder{
        private static StateList INSTANCE = new StateList();
    }


}
