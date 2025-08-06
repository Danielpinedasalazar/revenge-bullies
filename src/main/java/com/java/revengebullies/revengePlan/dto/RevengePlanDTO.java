package com.java.revengebullies.revengePlan.dto;

import com.java.revengebullies.bully.dto.BullyDTO;
import com.java.revengebullies.media.dto.MediaDTO;
import com.java.revengebullies.revengePlan.enums.Success;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevengePlanDTO {
    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "bullyId is required")
    @Positive(message = "bullyId must be positive")
    private Long bullyId;

    @NotNull(message = "datePlanned is required")
    private LocalDateTime datePlanned;
}
