package io.owen.jfc.config;


import io.owen.jfc.handler.PostHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;

@Configuration
@EnableWebFlux
//@ComponentScan("io.owen.jfc.handler")
public class WebConfig implements WebFluxConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PostHandler postHandler;

    @Bean
    public RouterFunction<ServerResponse> index(){
        return RouterFunctions.route(RequestPredicates.GET("/"), postHandler::index);
    }

    @Bean
    public RouterFunction<ServerResponse> keyboard(){
        return RouterFunctions.route(RequestPredicates.GET("/keyboard"), postHandler::handleKeyboards);
    }

    @Bean
    public RouterFunction<ServerResponse> message(){
        return RouterFunctions.route(RequestPredicates.POST("/message"), postHandler::handleMessage);
    }

    @Bean
    public RouterFunction<ServerResponse> postFriend(){
        return RouterFunctions.route(RequestPredicates.POST("/friend"), postHandler::handleAddFriend);
    }

    @Bean
    public RouterFunction<ServerResponse> deleteFriend(){
        return RouterFunctions.route(RequestPredicates.DELETE("/friend"), postHandler::handleDeleteFriend);
    }

    @Bean
    public SpringWebFluxTemplateEngine templateEngine(){
        SpringWebFluxTemplateEngine templateEngine = new SpringWebFluxTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("classpath:/templates/");

        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafReactiveViewResolver thymeleafReactiveViewResolver = new ThymeleafReactiveViewResolver();
        thymeleafReactiveViewResolver.setTemplateEngine(templateEngine());
        thymeleafReactiveViewResolver.setApplicationContext(this.applicationContext);

        registry.viewResolver(thymeleafReactiveViewResolver);
    }
}
