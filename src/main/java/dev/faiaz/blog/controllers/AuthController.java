package dev.faiaz.blog.controllers;

import dev.faiaz.blog.security.JwtAuthRequest;
import dev.faiaz.blog.security.JwtAuthResponse;
import dev.faiaz.blog.security.UserDetailServiceImpl;
import dev.faiaz.blog.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtils jwtUtils;
    private final UserDetailServiceImpl userDetailService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
            ){
        authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);

        return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
    }
    private void authenticate(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);
    }
}
