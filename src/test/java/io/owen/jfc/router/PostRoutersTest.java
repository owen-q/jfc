package io.owen.jfc.router;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;


/**
 * Created by owen_q on 2018. 7. 14..
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostRoutersTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void tc_사용자_등록(){
        webTestClient.post().uri("/friend")
                .body(BodyInserters.fromObject("test"))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void tc_getKeyboard(){
        webTestClient.get().uri("/keyboard")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void tc_handleMessage_with_expected_inputs(){
        webTestClient.post().uri("/message")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void tc_handleMessage_with_unexpected_inputs(){


    }
}