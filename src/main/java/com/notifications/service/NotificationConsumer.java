package com.notifications.service;

import com.projects.dto.ProjectEvent;
import com.notifications.dto.TaskEvent;
import com.notifications.entity.Notification;
import com.notifications.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final WebSocketService webSocketService;

    public NotificationConsumer(NotificationRepository notificationRepository,
                                WebSocketService webSocketService) {
        this.notificationRepository = notificationRepository;
        this.webSocketService = webSocketService;
    }

    @KafkaListener(topics = "task-topic", groupId =  "notification-group")
    public void consumeTask(TaskEvent event){
        if("TASK_ASSIGNED".equals(event.getEventType())){
            Notification notification=new Notification();
            notification.setUserId(event.getUserId());
            notification.setMessage("You were Assigned to "+event.getTaskTitle());
            notification.setType(event.getEventType());

            Notification saved=notificationRepository.save(notification);
            webSocketService.sendNotification(saved);
        }
    }

    @KafkaListener(topics = "project-topic", groupId = "notification-group")
    public void consumeProject(ProjectEvent projectEvent){
        Notification notification=new Notification();
        notification.setType(projectEvent.getEventType());
        notification.setUserId(projectEvent.getUserId());
        notification.setMessage("You were assigned to "+projectEvent.getProjectTitle());
        Notification saved=notificationRepository.save(notification);
        webSocketService.sendNotification(saved);
    }
}
