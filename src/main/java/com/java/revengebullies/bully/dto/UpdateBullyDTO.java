package com.java.revengebullies.bully.dto;

import com.java.revengebullies.bully.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBullyDTO {
    private String name;
    private String nickname;
    private Roles highSchoolRole;
    private Long cliqueId;         // si viene, reasigna
    private String bullyingReason;
    private Integer levelOfAnnoyance;
}
