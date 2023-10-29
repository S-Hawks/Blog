package dev.faiaz.blog.auth;

import dev.faiaz.blog.entities.User;
import dev.faiaz.blog.notification.EmailService;
import dev.faiaz.blog.repositories.UserRepository;
import dev.faiaz.blog.security.jwt.JwtUtils;
import dev.faiaz.blog.security.UserDetailImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = new User();
        user.setName(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAbout(request.getAbout());
        user.setRole(request.getRole());
        userRepository.save(user);
        var userDetail = new UserDetailImpl(user);
        var jwtToken = jwtUtils.generateToken(userDetail);
        //TODO: Sending mail after successful registration
/*        String to = request.getEmail();
        String subject = "Registration Conformation";
        String text = "Thank you for registering " + user.getName() + "!";
        emailService.sendEmail(to,subject,text)*/;

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
