package com.java.revengebullies.clique.dto;

import com.java.revengebullies.bully.dto.ResponseBullyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CliqueResponseDTO {
    private Long id;
    private String name;
    private String description;
    private List<ResponseBullyDTO> bullies;
}
