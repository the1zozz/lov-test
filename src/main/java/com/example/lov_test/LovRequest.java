package com.example.lov_test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LovRequest {
    private String lovCode;
    private String lovValue;
    private String descriptionEn;
    private String descriptionAr;
    private Boolean isActive;
}
