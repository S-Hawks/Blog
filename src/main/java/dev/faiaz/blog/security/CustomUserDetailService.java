package dev.faiaz.blog.security;

import dev.faiaz.blog.entities.User;
import dev.faiaz.blog.exceptions.ResourceNotFoundException;
import dev.faiaz.blog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("User", "email: " + username, 0)
        );
        return user;
    }
}
