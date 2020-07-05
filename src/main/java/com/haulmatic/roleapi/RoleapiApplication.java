package com.haulmatic.roleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RoleAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleAPIApplication.class, args);
    }

    @Bean
    public Docket roleApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.haulmatic.roleapi"))
                .build().apiInfo(new ApiInfoBuilder()
                        .title("Haulmatic Role API").version("1.0")
                        .description("Haulmatic REST API Documentation for Roles")
                        .build());
    }
}
