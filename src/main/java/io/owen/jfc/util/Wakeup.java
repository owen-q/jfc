package io.owen.jfc.util;

import io.owen.jfc.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by owen_q on 2018. 7. 13..
 */
@Component
public class Wakeup {
    private Logger logger = LoggerFactory.getLogger(Wakeup.class);

    private final String ZONE = "Asia/Seoul";
    private ZonedDateTime lastSelfHealedTime;

    private WebClient webClient;

    @Autowired
    private ApplicationConfig applicationConfig;

    public static Wakeup getInstance() {
        return Holder.INSTANCE;
    }
    
    public Wakeup() {
        this.lastSelfHealedTime = ZonedDateTime.now(ZoneId.of(ZONE));
    }

    public void remarkLastAccessedTime(){
        this.lastSelfHealedTime = ZonedDateTime.now(ZoneId.of(ZONE));
    }

    public void runTimer(){
        int duration = getTimerDuration(getNow());

        if(logger.isInfoEnabled())
            logger.info("[{}] runTimer() with interval {} minutes", getNow(), duration);

        // Stop timer for shutdown app
        if(duration == -1)
            return;

        Mono.delay(Duration.ofMinutes(duration))
            .subscribe(o->{
                healing();
                runTimer();
            });
    }

    private ZonedDateTime getNow(){
        return ZonedDateTime.now(ZoneId.of(ZONE));
    }

    public int getTimerDuration(ZonedDateTime now){
        int closeTime = applicationConfig.getCloseTime();
        int duration = 0;

        int nowHour = now.getHour();
        int nowMinute = now.getMinute();

        if(closeTime - nowHour <= 0){
            // should stop
            duration = -1;

        }
        else if(closeTime - nowHour == 1){
            // check minute

            duration = 60 - nowMinute;

            if(duration >= 30)
                duration = applicationConfig.getFreeInterval()-1;
        }
        else {
            duration = applicationConfig.getFreeInterval()-1;
        }

        return duration;
    }

    private void healing(){
        // Send GET /heal
        webClient = WebClient.create("/heal");

        Mono<ClientResponse> clientResponseMono = webClient.get().exchange();
        clientResponseMono.subscribe(clientResponse -> {
            if(clientResponse.statusCode() == HttpStatus.OK)
                if(logger.isInfoEnabled())
                    logger.info("Self-healed, {}", getNow().toString());
        });
    }

    @PostConstruct
    public void afterInit(){
        runTimer();
    }

    private static class Holder {
        private static Wakeup INSTANCE = new Wakeup();
    }
}
