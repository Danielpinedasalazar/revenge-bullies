package com.java.revengebullies.media.dto;

import com.java.revengebullies.media.enums.Type;
import com.java.revengebullies.revengePlan.models.RevengePlan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaDTO {
    @NotNull(message = "revengePlanId is required")
    @Positive(message = "revengePlanId must be positive")
    private Long revengePlanId;

    @NotNull(message = "type is required")
    private Type type;

    @NotBlank(message = "url is required")
    private String url;

    @NotBlank(message = "caption is required")
    private String caption;
}
