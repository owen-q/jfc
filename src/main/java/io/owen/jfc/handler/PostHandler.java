package io.owen.jfc.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.owen.jfc.UserRepository;
import io.owen.jfc.core.MessageHandler;
import io.owen.jfc.core.ResponseFactory;
import io.owen.jfc.core.StateManager;
import io.owen.jfc.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Aspect
@Component
public class PostHandler {
    private Logger logger = LoggerFactory.getLogger(PostHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private MessageHandler messageHandler;

    private StateManager stateManager;

    public PostHandler() {
        this.stateManager = StateManager.getInstance();
    }

    public Mono<ServerResponse> index(ServerRequest serverRequest){
        return ServerResponse.ok().render("index");
    }

    public Mono<ServerResponse> handleKeyboards(ServerRequest serverRequest){
        if(logger.isInfoEnabled())
            logger.info(serverRequest.toString());
//        stateManager.reset();

        // TODO:
        JsonNode jsonNode = responseFactory.createObjectNode("type", "buttons");
        JsonNode arrayNode = responseFactory.createArrayNode();

        ((ArrayNode) arrayNode).add("1");
        ((ArrayNode) arrayNode).add("2");
        ((ArrayNode) arrayNode).add("3");

        ((com.fasterxml.jackson.databind.node.ObjectNode) jsonNode).set("buttons", arrayNode);

        return ServerResponse.ok().body(BodyInserters.fromObject(jsonNode));
    }

    public Mono<ServerResponse> handleMessage(ServerRequest serverRequest){
        if(logger.isInfoEnabled()){
            logger.info(serverRequest.toString());
        }

        return serverRequest.bodyToMono(JsonNode.class).map(messageHandler::handle).flatMap((result)->{
            return ServerResponse.ok().body(BodyInserters.fromObject(result));
        });
    }

    public Mono<ServerResponse> handleAddFriend(ServerRequest serverRequest){
        if(logger.isInfoEnabled()) {
            logger.info("ADD Friend!");
            logger.info(serverRequest.headers().toString());
        }

        serverRequest.bodyToFlux(JsonNode.class).subscribe((requestBodyJsonNode)->{
            String userKey = "";
            userKey = requestBodyJsonNode.get("user_key").asText();

            if(!userKey.equals("")){
                if(logger.isInfoEnabled())
                    logger.info("Store user {}", userKey);
                // Save user_key to User.class
                userRepository.save(new User(userKey));
            }
        });

        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> handleDeleteFriend(ServerRequest serverRequest){
        if(logger.isInfoEnabled()) {
            logger.info("Delete Friend!");
        }

        serverRequest.queryParam("user_key").ifPresent(userKey -> {
            userRepository.delete(new User(userKey));
        });

        return ServerResponse.ok().build();
    }

    private Flux<JsonNode> readJsonRequestBody(ServerRequest serverRequest){
        return serverRequest.bodyToFlux(JsonNode.class);
    }
}
