package com.notifications.service;

import com.notifications.entity.Notification;
import com.notifications.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public List<Notification> getByUser(Integer userId) {
        return repository.findByUserId(userId);
    }

    public void markAsRead(Integer id) {
        Notification n = repository.findById(id).orElseThrow();
        n.setIsRead(true);
        repository.save(n);
    }
}
