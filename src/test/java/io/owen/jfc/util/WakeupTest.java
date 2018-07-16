package io.owen.jfc.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by owen_q on 2018. 7. 14..
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WakeupTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private Wakeup wakeup;

    @Test
    public void testWakeupTimer(){
//        wakeup.runTimer();
    }

    @Test
    public void tc_getTimerDuration(){
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        int duration = wakeup.getTimerDuration(now);

        assertEquals(duration, 29);
    }
}