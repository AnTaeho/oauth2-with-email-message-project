package com.example.oauth2withemailmessageproject.service;

import com.example.oauth2withemailmessageproject.controller.dto.JoinRequest;
import com.example.oauth2withemailmessageproject.controller.dto.LoginRequest;
import com.example.oauth2withemailmessageproject.domain.User;
import com.example.oauth2withemailmessageproject.domain.UserRepository;
import com.example.oauth2withemailmessageproject.event.JoinEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final ApplicationEventPublisher publisher;

    public boolean checkLoginIdDuplicate(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public void join2(JoinRequest req) {
        User user = userRepository.save(req.toEntity(encoder.encode(req.getPassword())));
        publisher.publishEvent(new JoinEvent(user));
    }

    public User getLoginUserByLoginId(String loginId) {
        if(loginId == null) return null;

        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        return optionalUser.orElse(null);

    }
}
