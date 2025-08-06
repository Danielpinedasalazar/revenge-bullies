package com.java.revengebullies.clique.dto;

import com.java.revengebullies.bully.models.Bully;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CliqueDTO {

    @NotBlank(message = "fill name")
    private String name;

    @NotBlank(message = "fill description")
    private String description;
}
