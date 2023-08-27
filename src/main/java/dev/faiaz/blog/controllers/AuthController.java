package dev.faiaz.blog.controllers;

import dev.faiaz.blog.payloads.JwtAuthRequest;
import dev.faiaz.blog.payloads.JwtAuthResponse;
import dev.faiaz.blog.security.CustomUserDetailService;
import dev.faiaz.blog.security.JwtTokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenHelper jwtTokenHelper;
    private final CustomUserDetailService userDetailService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
            ){
        return null;
    }
}
