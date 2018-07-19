package io.owen.jfc.aop;

import io.owen.jfc.commands.Command;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by owen_q on 2018. 7. 15..
 */
@Aspect
public class CommandPreprocessor {
    private Logger logger = LoggerFactory.getLogger(CommandPreprocessor.class);

    @Pointcut("@annotation(io.owen.jfc.commands.Command)")
    public void beforeCommandAnnotation() {

    }

    @Before("beforeCommandAnnotation()")
    public void logCommand(JoinPoint joinPoint){
        Command command = joinPoint.getClass().getDeclaredAnnotation(Command.class);

        if(logger.isInfoEnabled()){
            logger.info("Command:: {}", command.name());
        }
    }
}
