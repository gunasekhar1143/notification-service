package com.notifications.controller;

import com.notifications.entity.Notification;
import com.notifications.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public List<Notification> getNotifications(@PathVariable Integer userId) {
        return service.getByUser(userId);
    }

    @PutMapping("/read/{id}")
    public void markRead(@PathVariable Integer id) {
        service.markAsRead(id);
    }
}