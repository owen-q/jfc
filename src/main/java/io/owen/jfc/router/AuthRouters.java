package io.owen.jfc.router;

import io.owen.jfc.core.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by owen_q on 2018. 7. 16..
 */
@Component
public class AuthRouters {
    private Logger logger = LoggerFactory.getLogger(AuthRouters.class);


    @Autowired
    MessageHandler messageHandler;

    public Mono<ServerResponse> handleAuth(ServerRequest serverRequest){

        return ServerResponse.ok().build();
    }
}
