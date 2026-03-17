package com.notifications.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEvent {

    private String eventType;
    private Integer taskId;
    private String taskTitle;
    private Integer userId;

}
