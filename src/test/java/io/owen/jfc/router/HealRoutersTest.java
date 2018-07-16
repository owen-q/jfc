package io.owen.jfc.router;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Created by owen_q on 2018. 7. 13..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealRoutersTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testSelfHeal(){
        webTestClient.get().uri("/heal")
                .exchange()
                .expectStatus().isOk();
    }
}