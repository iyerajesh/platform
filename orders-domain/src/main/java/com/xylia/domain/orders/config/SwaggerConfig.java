package com.xylia.domain.orders.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Orders Domain - Orders API")
                .description("Orders domain CQRS application, backed by MongoDB and Kafka.")
                .contact(new Contact(
                        "Rajesh Iyer", "",
                        "iyerajesh@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/iyerajesh/platform/LICENSE")
                .version("1.0.0")
                .build();
    }
}
