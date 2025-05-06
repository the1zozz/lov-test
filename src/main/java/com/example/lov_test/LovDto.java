package com.example.lov_test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class LovDto {
    private Long id;
    private String lovCode;
    private String lovValue;
    private String description;
    private Boolean isActive;
}
