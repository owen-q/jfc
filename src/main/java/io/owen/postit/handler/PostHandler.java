package io.owen.postit.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PostHandler {
    private Logger logger = LoggerFactory.getLogger(PostHandler.class);

    /*
    @Autowired
    private WebClient webClient;
    */

    public Mono<ServerResponse> index(ServerRequest serverRequest){
        return ServerResponse.ok().render("index");
    }

    public Mono<ServerResponse> handleKeyboards(ServerRequest serverRequest){
        if(logger.isInfoEnabled()){
            ;
        }

        JsonNodeFactory jsonNodeFactory = new JsonNodeFactory(false);
        JsonNode jsonNode = new ObjectNode(jsonNodeFactory);

        ((ObjectNode) jsonNode).put("type", "text");

        return ServerResponse.ok().body(BodyInserters.fromObject(jsonNode));
    }

    public Mono<ServerResponse> handleMessage(ServerRequest serverRequest){
        if(logger.isInfoEnabled()){
            logger.info("Handle Message!");
            logger.info(serverRequest.toString());
        }

        JsonNodeFactory jsonNodeFactory = new JsonNodeFactory(false);
        JsonNode result = new ObjectNode(jsonNodeFactory);
        JsonNode message = new ObjectNode(jsonNodeFactory);
        JsonNode messageButton = new ObjectNode(jsonNodeFactory);


        ((ObjectNode) message).put("text", "안녕하세요");
        ((ObjectNode) messageButton).put("label", "label!");
        ((ObjectNode) messageButton).put("url", "http://e422bdb2.ngrok.io");
//        ((ObjectNode) messageButton).put("url", "https://naver.com");

        ((ObjectNode) message).set("message_button", messageButton);
        ((ObjectNode) result).set("message", message);
//        ((ObjectNode) result).set("messageButton", messageButton);

        return ServerResponse.ok().body(BodyInserters.fromObject(result));
    }
}
