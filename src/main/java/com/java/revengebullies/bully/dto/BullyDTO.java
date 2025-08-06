package com.java.revengebullies.bully.dto;

import com.java.revengebullies.bully.enums.Roles;
import com.java.revengebullies.clique.models.Clique;
import com.java.revengebullies.revengePlan.models.RevengePlan;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BullyDTO {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "nickname is required")
    private String nickname;

    @NotNull(message = "highSchoolRole is required")
    private Roles highSchoolRole;

    @NotNull(message = "cliqueId is required")
    @Positive(message = "cliqueId must be positive")
    private Long cliqueId;

    @NotBlank(message = "bullyingReason is required")
    private String bullyingReason;

    @Min(value = 1, message = "levelOfAnnoyance must be >= 1")
    @Max(value = 10, message = "levelOfAnnoyance must be <= 10")
    private int levelOfAnnoyance;
}
