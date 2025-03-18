package io.company.outsera.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class LojaDTO {

    private Number id;
    private Number petId;
    private Number quantity;
    @Builder.Default
    private String shipDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    private String status;
    @Builder.Default
    private Boolean complete = Boolean.TRUE;


}
