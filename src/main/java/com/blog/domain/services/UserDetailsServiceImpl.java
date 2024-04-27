package com.blog.domain.services;

import com.blog.data.models.CustomUserDetails;
import com.blog.data.models.User;
import com.blog.data.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        logger.debug("Entering in loadUserByUsername Method...");
        User user = userRepository.findByEmail(email);
        if(user == null){
            logger.error("Email not found: {}", email);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        logger.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(user);
    }
}
