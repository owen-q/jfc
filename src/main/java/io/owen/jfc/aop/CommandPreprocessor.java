package io.owen.jfc.aop;

import io.owen.jfc.commands.Command;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by owen_q on 2018. 7. 15..
 */
@Aspect
public class CommandPreprocessor {
    private Logger logger = LoggerFactory.getLogger(CommandPreprocessor.class);

    @Before("@annotation(Command)")
    public void beforeCommandAnnotation(ProceedingJoinPoint proceedingJoinPoint){
        Command command = proceedingJoinPoint.getClass().getDeclaredAnnotation(Command.class);

        if(logger.isInfoEnabled()){
            logger.info("Command:: {}", command.name());
        }
    }
}
