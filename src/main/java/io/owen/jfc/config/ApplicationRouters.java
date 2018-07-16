package io.owen.jfc.config;

import io.owen.jfc.router.AuthRouters;
import io.owen.jfc.router.HealRouters;
import io.owen.jfc.router.PostRouters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Created by owen_q on 2018. 7. 14..
 */
@Configuration
@EnableWebFlux
public class ApplicationRouters {
    private Logger logger = LoggerFactory.getLogger(ApplicationRouters.class);

    @Autowired
    private PostRouters postRouters;

    @Autowired
    private HealRouters healRouters;

    @Autowired
    private AuthRouters authRouters;

    @Bean
    public RouterFunction<ServerResponse> auth(){
        return RouterFunctions.route(RequestPredicates.POST("/auth"), authRouters::auth);
    }
    @Bean
    public RouterFunction<ServerResponse> index(){
        return RouterFunctions.route(RequestPredicates.GET("/"), postRouters::index);
    }

    @Bean
    public RouterFunction<ServerResponse> keyboard(){
        return RouterFunctions.route(RequestPredicates.GET("/keyboard"), postRouters::handleKeyboards);
    }

    @Bean
    public RouterFunction<ServerResponse> message(){
        return RouterFunctions.route(RequestPredicates.POST("/message"), postRouters::handleMessage);
    }

    @Bean
    public RouterFunction<ServerResponse> postFriend(){
        return RouterFunctions.route(RequestPredicates.POST("/friend"), postRouters::handleAddFriend);
    }

    @Bean
    public RouterFunction<ServerResponse> deleteFriend(){
        return RouterFunctions.route(RequestPredicates.DELETE("/friend"), postRouters::handleDeleteFriend);
    }

    @Bean
    public RouterFunction<ServerResponse> heal(){
        return RouterFunctions.route(RequestPredicates.GET("/heal"), healRouters::heal);
    }
}
