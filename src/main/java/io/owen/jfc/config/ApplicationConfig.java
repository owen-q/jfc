package io.owen.jfc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by owen_q on 2018. 7. 14..
 */

@Configuration
@PropertySource("classpath:working-time.properties")
@Component
public class ApplicationConfig {
    @Value("${working.time.open}")
    private int openTime;

    @Value("${working.time.close}")
    private int closeTime;

    @Value("${heroku.free.interval}")
    private int freeInterval;

    public int getOpenTime() {
        return openTime;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public int getFreeInterval() {
        return freeInterval;
    }


    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }

    public void setFreeInterval(int freeInterval) {
        this.freeInterval = freeInterval;
    }
}
