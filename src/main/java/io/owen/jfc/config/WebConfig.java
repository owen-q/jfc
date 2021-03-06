package io.owen.jfc.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;

@Configuration
@EnableWebFlux
@EnableAspectJAutoProxy
public class WebConfig implements WebFluxConfigurer {
    @Autowired
    private ApplicationContext applicationContext;

    // Set TemplateEngine
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
