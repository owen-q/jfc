package io.owen.jfc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

/**
 * Created by owen_q on 2018. 7. 16..
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthTest {
    private Logger logger = LoggerFactory.getLogger(AuthTest.class);

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void 인증하기(){
        webTestClient.post().uri("/handleAuth")
                .body(BodyInserters.fromObject("owen"))
                .exchange()
                .expectStatus().isOk();
    }
}
