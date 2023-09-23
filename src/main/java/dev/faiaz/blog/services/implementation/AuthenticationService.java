package dev.faiaz.blog.services.implementation;

import dev.faiaz.blog.entities.Role;
import dev.faiaz.blog.entities.User;
import dev.faiaz.blog.repositories.UserRepository;
import dev.faiaz.blog.security.JwtUtils;
import dev.faiaz.blog.security.UserDetailImpl;
import dev.faiaz.blog.security.payload.AuthenticationRequest;
import dev.faiaz.blog.security.payload.AuthenticationResponse;
import dev.faiaz.blog.security.payload.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = new User();
        user.setName(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAbout(request.getAbout());
        user.setRoles(Set.of(Role.ROLE_ADMIN));
        userRepository.save(user);
        var userDetail = new UserDetailImpl(user);
        var jwtToken = jwtUtils.generateToken(userDetail);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  request.getEmail(),
                  request.getPassword()
          )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var userDetails = new UserDetailImpl(user);
        var jwtToken = jwtUtils.generateToken(userDetails);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}