package io.owen.jfc.commands.attend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;

/**
 * Created by owen_q on 2018. 7. 23..
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MatchListStateTest {

    @Autowired
    private WebTestClient webTestClient;

    /*
        curl -XPOST 'https://:your_server_url/message' -d '{
      "user_key": "encryptedUserKey",
      "type": "text",
      "content": "차량번호등록"
    }'
     */
    @Test
    public void 경기일정_참석시도() {
        // Given:
        String userKey = "aEHJMiBKYLCV";
        String type = "text";
        String content = "> 2018-07-29 (일) 참석:0, 불참:0";

        JsonNodeFactory jsonNodeFactory = new JsonNodeFactory(false);
        JsonNode body = new ObjectNode(jsonNodeFactory);

        ((ObjectNode) body).put("user_key", userKey);
        ((ObjectNode) body).put("type", type);
        ((ObjectNode) body).put("content", content);

        // When:
        Flux<String> response = webTestClient.post().uri("/message").body(BodyInserters.fromObject(body))
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class).getResponseBody();
        
        // Then:

    }

    @Test
    public void 참석시도() {
        // Given:
        String userKey = "aEHJMiBKYLCV";
        String type = "text";
        String content = "참석";

        JsonNodeFactory jsonNodeFactory = new JsonNodeFactory(false);
        JsonNode body = new ObjectNode(jsonNodeFactory);

        ((ObjectNode) body).put("user_key", userKey);
        ((ObjectNode) body).put("type", type);
        ((ObjectNode) body).put("content", content);

        // When:
        Flux<String> response = webTestClient.post().uri("/message").body(BodyInserters.fromObject(body))
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class).getResponseBody();

        // Then:

    }

}