package com.java.revengebullies.bully.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseBullyDTO {
    private Long id;
    private String name;
    private String nickname;
    private String highSchoolRole;   // .name()
    private String cliqueName;       // resumen
    private int levelOfAnnoyance;
}
