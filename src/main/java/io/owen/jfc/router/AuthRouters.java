package io.owen.jfc.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public Mono<ServerResponse> auth(ServerRequest serverRequest){
        return ServerResponse.ok().build();
    }
}
