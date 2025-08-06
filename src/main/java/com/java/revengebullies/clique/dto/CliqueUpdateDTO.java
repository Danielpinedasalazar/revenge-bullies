package com.java.revengebullies.clique.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CliqueUpdateDTO {
    private String name;
    private String description;
}
