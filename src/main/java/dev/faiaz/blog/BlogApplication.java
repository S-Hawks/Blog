package dev.faiaz.blog;

import dev.faiaz.blog.security.payload.RegisterRequest;
import dev.faiaz.blog.services.implementation.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static dev.faiaz.blog.entities.Role.ADMIN;

@SpringBootApplication
@RequiredArgsConstructor
public class BlogApplication {

	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service){
		return args -> {
			var admin  = RegisterRequest.builder()
					.username("faiaz")
					.email("faiaz@email.com")
					.password("admin_faiaz")
					.about("Admin about")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getToken());
		};
	}

}
