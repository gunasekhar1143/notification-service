package com.projects.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectEvent {
    private String eventType;
    private Integer projectId;
    private String projectTitle;
    private Integer userId;
}
