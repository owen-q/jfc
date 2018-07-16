package io.owen.jfc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Created by owen_q on 2018. 7. 16..
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeTest {
    private Logger logger = LoggerFactory.getLogger(HomeTest.class);

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void tc_홈_입장(){
        EntityExchangeResult<byte[] > entityExchangeResult = webTestClient.get().uri("/keyboard")
                .exchange()
                .expectStatus().isOk()
                .expectBody().returnResult();

        String response = new String(entityExchangeResult.getResponseBody());

        org.junit.Assert.assertTrue(response.contains("홈"));
    }

    @Test
    public void tc_홈_메뉴선택(){

    }
}
