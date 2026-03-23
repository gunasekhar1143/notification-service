package com.notifications.service;

import com.notifications.entity.Notification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(Notification notification) {

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + notification.getUserId(),
                notification
        );
    }
}
