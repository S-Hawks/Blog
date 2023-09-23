package dev.faiaz.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact
                        (name = "faiaz", email = "faiazibneahsan9@gmial.com"),
                description = "Api Documentation for Blog",
                title = "Blog Api documentation",
                version = "1.0",
                license = @License(
                        name = "name of licence",
                        url = "someUrl.com"
                ),
                termsOfService = "some term of services"
        ),
        servers = {@Server(description = "1st server description", url = "https://url.com"), @Server(description = "2nd server", url = "https://url.com")}
)
public class SwaggerConfig {

}
