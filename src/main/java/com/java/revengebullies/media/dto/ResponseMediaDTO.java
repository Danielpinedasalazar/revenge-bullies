package com.java.revengebullies.media.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseMediaDTO {
    private Long id;
    private String type;
    private String url;
    private String caption;
}
