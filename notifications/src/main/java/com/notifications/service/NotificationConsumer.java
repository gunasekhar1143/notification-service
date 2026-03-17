package com.notifications.service;

import com.notifications.dto.TaskEvent;
import com.notifications.entity.Notification;
import com.notifications.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private NotificationRepository notificationRepository;

    public NotificationConsumer(NotificationRepository notificationRepository){
        this.notificationRepository=notificationRepository;
    }

    @KafkaListener(topics = "task-topic", groupId =  "notification-group")
    public void consume(TaskEvent event){
        if("TASK_ASSIGNED".equals(event.getEventType())){
            Notification notification=new Notification();
            notification.setUserId(event.getUserId());
            notification.setMessage("You were Assigned to "+event.getTaskTitle());
            notification.setType(event.getEventType());

            notificationRepository.save(notification);
        }
    }
}
