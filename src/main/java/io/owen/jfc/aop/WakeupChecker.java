package io.owen.jfc.aop;

import io.owen.jfc.util.Wakeup;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by owen_q on 2018. 7. 13..
 */
@Aspect
@Component
public class WakeupChecker {
    private Logger logger = LoggerFactory.getLogger(WakeupChecker.class);

    private Wakeup wakeup;

    public WakeupChecker() {
        wakeup = Wakeup.getInstance();
    }

    @Before("io.owen.jfc.aop.WakeupChecker.onPointcut()")
    public void onBefore(){
        wakeup.remarkLastAccessedTime();
    }

    @Pointcut("within(io.owen.jfc.router.*.handle)")
    public void onPointcut() {
    }


    @Before("execution(* io.owen.jfc.commands.*.*State.handle())")
    public void onBeforeHandle(){
        if(logger.isInfoEnabled())
            logger.info("onBeforeHandle()");
    }

}
