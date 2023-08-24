package com.nhantran.todoapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "NhanBrown",
                        email = "nhan.tran@bkit.solutions"
                ),
                title = "TodoApp's Library APIs",
                version = "1.0",
                description = "TodoApp Library Management APIs of Brown's Intern trainning Project 2023 at BKIT SOLUTIONS "
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8888"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerOpenApiConfig {
}
