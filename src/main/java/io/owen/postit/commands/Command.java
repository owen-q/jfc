package io.owen.postit.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by owen_q on 2018. 7. 11..
 */

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Command {
    // name
    String name();

    // current state
    UserState state();

    UserState[] availableNextState();
}
