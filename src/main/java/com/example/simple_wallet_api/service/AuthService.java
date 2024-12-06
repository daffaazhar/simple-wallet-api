package com.example.simple_wallet_api.service;

import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.auth.LoginResponse;
import com.example.simple_wallet_api.model.auth.LoginUserRequest;
import com.example.simple_wallet_api.model.auth.MeResponse;
import com.example.simple_wallet_api.model.auth.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.simple_wallet_api.repository.UserRepository;
import com.example.simple_wallet_api.security.BCrypt;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void register(RegisterUserRequest request) {
        validationService.validate(request);

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email address already registered");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));

        userRepository.save(user);
    }

    @Transactional
    public LoginResponse login(LoginUserRequest request) {
        validationService.validate(request);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());
            userRepository.save(user);

            return LoginResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
    }

    public MeResponse me(User user) {
        return MeResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Transactional
    public void logout(User user) {
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }
}
