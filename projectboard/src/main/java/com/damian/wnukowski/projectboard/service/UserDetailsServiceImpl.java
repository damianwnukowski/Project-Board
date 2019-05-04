package com.damian.wnukowski.projectboard.service;

import com.damian.wnukowski.projectboard.entity.Role;
import com.damian.wnukowski.projectboard.entity.User;
import com.damian.wnukowski.projectboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Role r : user.getRoles()){
            grantedAuthorities.add(r::getRoleName);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }
}
