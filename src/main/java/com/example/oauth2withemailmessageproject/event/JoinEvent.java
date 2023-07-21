package com.example.oauth2withemailmessageproject.event;

import com.example.oauth2withemailmessageproject.domain.User;
import lombok.Data;

@Data
public class JoinEvent {
    private User user;

    public JoinEvent(User user) {
        this.user = user;
    }
}
