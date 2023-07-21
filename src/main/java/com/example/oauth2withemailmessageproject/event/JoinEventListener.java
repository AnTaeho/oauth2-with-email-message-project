package com.example.oauth2withemailmessageproject.event;

import com.example.oauth2withemailmessageproject.controller.dto.MailRequest;
import com.example.oauth2withemailmessageproject.domain.User;
import com.example.oauth2withemailmessageproject.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JoinEventListener {

    private final MailService mailService;

    @EventListener
    public void sendMail(JoinEvent event) {
        User user = event.getUser();

        log.info("메일 전송 시작");
        MailRequest mailRequest = new MailRequest();
        mailRequest.setAddress(user.getEmail());
        mailRequest.setTitle("회원가입을 환영합니다!");
        mailRequest.setMessage(user.getNickname() + "님 가입을 환영합니다.");
        mailService.sendMail(mailRequest);
        log.info("메일 전송 정상 완료");
    }
}

