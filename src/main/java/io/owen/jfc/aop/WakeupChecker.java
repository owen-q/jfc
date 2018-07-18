package io.owen.jfc.aop;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.util.Wakeup;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.stream.Stream;

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

    @Before("io.owen.jfc.aop.WakeupChecker.httpRequestLog()")
    public void onBefore(JoinPoint joinPoint){
        
        if(logger.isInfoEnabled()) {
            Stream.of(joinPoint.getArgs()).filter(arg -> arg instanceof ServerRequest).forEach(serverRequest -> {
                StringBuilder requestLogBuilder = new StringBuilder();
                requestLogBuilder.append(((ServerRequest) serverRequest).method());
                requestLogBuilder.append(" " + ((ServerRequest) serverRequest).path());
                requestLogBuilder.append("\n");


                if(((ServerRequest) serverRequest).method() == HttpMethod.GET){
                    ((ServerRequest) serverRequest).queryParams().keySet().stream().forEach(queryParam -> requestLogBuilder.append(queryParam));
                }
                else if (((ServerRequest) serverRequest).method() == HttpMethod.POST){
                    ((ServerRequest) serverRequest).bodyToMono(JsonNode.class).subscribe(jsonNode -> {
                        requestLogBuilder.append(jsonNode.toString());
                    });
                }

                logger.info(requestLogBuilder.toString());
            });
        }

//        wakeup.remarkLastAccessedTime();
    }

    @Pointcut("within(io.owen.jfc.router.*)")
    public void httpRequestLog() {
    }

    @Before(value = "execution(* io.owen.jfc.commands.*.*State.handle())")
    public void onBeforeHandle(){
        if(logger.isInfoEnabled())
            logger.info("onBeforeHandle()");

    }

}
