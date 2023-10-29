package dev.faiaz.blog.security;

import dev.faiaz.blog.security.jwt.JwtAuthenticationEntryPoint;
import dev.faiaz.blog.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static dev.faiaz.blog.entities.Permission.*;
import static dev.faiaz.blog.entities.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final UserDetailServiceImpl userDetailServiceImpl;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

//    private final UserRepository userRepository;
    public static final String[] PUBLIC_URLS = {
        "/api/v1/auth/**",
        //Here all url related to swagger. don't need to add everything, here put it all for future reference.
        "/v2/api-docs",
        "/v3/api-docs",
        "/v3/api-docs/**",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui/**",
        "/webjars/**",
        "/swagger-ui.html"
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
           return http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(authorize -> authorize.requestMatchers(PUBLIC_URLS).permitAll()

                            .requestMatchers("/api/users/**").hasAnyRole(ADMIN.name(),MODERATOR.name(), USER.name())
                            .requestMatchers(GET,"api/users/**").hasAnyAuthority(ADMIN_READ.name(),MODERATOR_READ.name())
                            .requestMatchers(DELETE,"/api/users/**").hasAnyAuthority(ADMIN_DELETE.name(),MODERATOR_DELETE.name())
                            .requestMatchers(PUT, "/api/users/**").hasAnyAuthority(ADMIN_UPDATE.name(), MODERATOR_UPDATE.name(),USER.name())
                            .requestMatchers(POST, "/api/users/**").hasAnyAuthority(ADMIN.name(),MODERATOR.name(),USER.name())

                            .requestMatchers(GET, "/api/posts/**").hasAnyAuthority(USER.name())
                            .requestMatchers(PUT, "/api/posts/**").hasAnyAuthority(USER.name())

                            .anyRequest().authenticated())
                    .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
    }
//    TODO: Previously used
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
//    }
//    TODO:Now in use
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //TODO: Alternative way for implementing UserDetailsService
    //TODO: IN this process we can avoid creating UserDetailsService implementation. Just declare it in this manner in configuration class of spring security
    //TODO: THINGS TO REMEMBER -> This thing need to configure in separate application config class
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return username -> (UserDetails) userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
//    }

}
