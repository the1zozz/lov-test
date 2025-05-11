package com.example.lov_test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class LovResponse {
    private Long id;
    private String lovCode;
    private String lovValue;
    private String descriptionEn;
    private String descriptionAr;
    private Boolean isActive;
}
