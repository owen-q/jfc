package io.owen.jfc.router;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.owen.jfc.common.entity.User;
import io.owen.jfc.common.repository.UserRepository;
import io.owen.jfc.core.MessageHandler;
import io.owen.jfc.core.ResponseFactory;
import io.owen.jfc.core.StateList;
import io.owen.jfc.core.StateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component

public class PostRouters {
    private Logger logger = LoggerFactory.getLogger(PostRouters.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private MessageHandler messageHandler;



    private StateManager stateManager;

    private StateList stateList;

    public PostRouters() {
        this.stateManager = StateManager.getInstance();
        this.stateList = StateList.getInstance();
    }

    public Mono<ServerResponse> index(ServerRequest serverRequest){
        return ServerResponse.ok().render("index");
    }

    public Mono<ServerResponse> handleKeyboards(ServerRequest serverRequest){
//        if(logger.isInfoEnabled())
//            logger.info(serverRequest.toString());

        JsonNode jsonNode = responseFactory.createObjectNode("type", "buttons");
        JsonNode arrayNode = responseFactory.createArrayNode();
        List<String> mainCommands = stateList.getMainCommands();

        mainCommands.stream().forEach(mainCommand -> ((ArrayNode) arrayNode).add(mainCommand));

        ((com.fasterxml.jackson.databind.node.ObjectNode) jsonNode).set("buttons", arrayNode);

        return ServerResponse.ok().body(BodyInserters.fromObject(jsonNode));
    }

    public Mono<ServerResponse> handleMessage(ServerRequest serverRequest){
        if(logger.isInfoEnabled()){
            logger.info(serverRequest.toString());
        }

        return serverRequest.bodyToMono(JsonNode.class).map(requestBody -> messageHandler.handle(serverRequest, requestBody)).flatMap((result)->{
            if(logger.isInfoEnabled())
                logger.info(result.toString());
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

        String friendId = serverRequest.pathVariable("id");

        userRepository.delete(new User(friendId));

        return ServerResponse.ok().build();
    }

    private Flux<JsonNode> readJsonRequestBody(ServerRequest serverRequest){
        return serverRequest.bodyToFlux(JsonNode.class);
    }
}
