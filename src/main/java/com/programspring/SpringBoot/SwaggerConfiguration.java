package com.programspring.SpringBoot;

import org.assertj.core.util.Sets;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfiguration {

    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
            .host("http://localhost:8080/api/v1") 
            .produces(Sets.newHashSet())
            .select()                                 
            .apis(RequestHandlerSelectors.any())              
            .paths(PathSelectors.any())                          
            .build();                                           
    }
    
}
