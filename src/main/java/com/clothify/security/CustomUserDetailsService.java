package com.clothify.security;

import com.clothify.domain.User;
import com.clothify.domain.UserEntityGraph;
import com.clothify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username, UserEntityGraph.____().roles().____.____())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return CustomUserDetails.create(user);
    }

}
