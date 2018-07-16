package io.owen.jfc.router;

import io.owen.jfc.util.Wakeup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by owen_q on 2018. 7. 13..
 */
@Component
public class HealRouters {
    private Logger logger = LoggerFactory.getLogger(HealRouters.class);

    private Wakeup wakeup;

    public HealRouters() {
        this.wakeup = Wakeup.getInstance();
    }

    public Mono<ServerResponse> heal(ServerRequest serverRequest){
        return ServerResponse.ok().build();
    }

}
