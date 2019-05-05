package com.damian.wnukowski.projectboard.web;

import com.damian.wnukowski.projectboard.entity.User;
import com.damian.wnukowski.projectboard.repository.UserRepository;
import com.damian.wnukowski.projectboard.util.BindingResultErrorMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BindingResultErrorMapper bindingResultErrorMapper;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                          BindingResultErrorMapper bindingResultErrorMapper){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.bindingResultErrorMapper = bindingResultErrorMapper;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user, BindingResult result){
        ResponseEntity<?> res = bindingResultErrorMapper.mapBindingResults(result);
        if(res!=null) return res;

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED); //We don't want to send user and his password (even though hashed) back
    }
}
