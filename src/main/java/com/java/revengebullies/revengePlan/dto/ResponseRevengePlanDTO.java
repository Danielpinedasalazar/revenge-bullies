package com.java.revengebullies.revengePlan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseRevengePlanDTO {
    private Long id;
    private String title;
    private String description;
    private String bullyName;        // resumen amigable
    private Boolean isExecuted;
    private LocalDateTime datePlanned;
    private String successLevel;
}
