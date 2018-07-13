package io.owen.jfc.commands;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Created by owen_q on 2018. 7. 11..
 */
public class StateRouter {
    private Logger logger = LoggerFactory.getLogger(StateRouter.class);
    private Map<String, Class<? extends Command>> commandList;

    public StateRouter() {
        Reflections reflections = new Reflections("io.owen.jfc");
        Set<Class<?>> commands = reflections.getTypesAnnotatedWith(Command.class);

        /*
        commands.stream().forEach(commandClass -> {
            commandClass.getConstructor();
        });
        */

        try {

            for(Class commandClass : commands) {
                Command tmp = (Command) commandClass.getConstructor().newInstance();
            }
        }
        catch (Exception e){

        }

    }

    public void route(){

        // current state
        // next state

        // check illegal

        // call next state handle

        // return result

        // change current state to next state
    }
}
