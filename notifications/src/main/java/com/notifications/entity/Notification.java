package com.notifications.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String message;

    private String type;

    private Boolean isRead = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    public void onCreate(){
        this.createdAt=LocalDateTime.now();
    }

}
