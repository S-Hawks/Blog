package dev.faiaz.blog.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class PasswordHelper implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("orko123"));
    }
}
