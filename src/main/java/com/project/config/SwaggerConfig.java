package com.project.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme( // ✅ Define the security scheme separately
	    name = "bearerAuth",
	    type = SecuritySchemeType.HTTP,
	    scheme = "bearer",
	    bearerFormat = "JWT",
	    in = SecuritySchemeIn.HEADER,
	    description = "Enter JWT token in the format: Bearer <token>"
	)
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Texi Booking API Documentation") // Change title here
                .version("1.0.0")
                .description("This is a comprehensive documentation for My Custom API. " +
                           "Here you can find all endpoints and their specifications.")
                .termsOfService("https://myapi.com/terms")
//                .contact(new Contact()
//                    .name("API Support")
//                    .url("https://myapi.com/support")
//                    .email("support@myapi.com"))
//                .license(new License()
//                    .name("Apache 2.0")
//                    .url("https://springdoc.org")
            );
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                .components(new Components().addSecuritySchemes(
//                		"bearerAuth", new SecurityScheme()
//                                .name("Authorization")
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT")
////                                .in(SecuritySchemeIn.)
//                                .description("Enter JWT token in the format: Bearer <token>"))
//                );
    }
}