package com.java.revengebullies.revengePlan.dto;

import com.java.revengebullies.revengePlan.enums.Success;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRevengePlanDTO {
    private String title;
    private String description;
    private Boolean isExecuted;
    private Success successLevel;
    private LocalDateTime datePlanned;
}
