package io.company.outsera.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AnimalDTO {

    private Number id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tags> tags;
    private String status;

    @Data
    @Builder
    public static class Category {
        private Number id;
        private String name;

    }
    @Data
    @Builder
    public static class Tags {
        private Number id;
        private String name;

    }

}
