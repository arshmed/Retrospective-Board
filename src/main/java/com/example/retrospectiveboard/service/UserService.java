package com.example.retrospectiveboard.service;

import com.example.retrospectiveboard.domain.User;
import com.example.retrospectiveboard.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new
                org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
    }

    @Transactional(rollbackFor = Exception.class)
    public User create(User user) {
        return userRepository.save(user);
    }
}