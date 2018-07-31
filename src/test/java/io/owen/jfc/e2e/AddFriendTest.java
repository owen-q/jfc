package io.owen.jfc.e2e;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

/**
 * Created by owen_q on 2018. 7. 25..
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddFriendTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void 친구추가() {
        // Given:
        String userKey = "test";
        String type = "text";
        String message = "";

        JsonNodeFactory jsonNodeFactory = new JsonNodeFactory(false);
        JsonNode jsonNode = new ObjectNode(jsonNodeFactory);

        ((ObjectNode) jsonNode).put("user_key", userKey);

        // When:
        webTestClient.post().uri("/friend").body(BodyInserters.fromObject(jsonNode))
        .exchange()
        .expectStatus().isOk();

        // Then:


    }


}
