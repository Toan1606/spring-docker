package com.codedecode.demo.service;

import java.util.ArrayList;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Role;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.UserRepository;

@Service
@Transactional
class UserDetailsServiceImpl implements UserDetailsService {


    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;


    @Autowired
    public UserDetailsServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	// if this is thrown, then we won't generate JWT token.
        User user = userRepository.findByEmail(email);
        Set<Role> roles = user.getRoles();
        System.out.println("roles : " + roles);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}