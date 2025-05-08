package com.example.lov_test;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LovRequest {
    @NotBlank(message = "LOV code is required")
    private String lovCode;

    @NotBlank(message = "LOV value is required")
    private String lovValue;

    @NotBlank(message = "English Description is required")
    @Size(max = 255, message = "English description must be 255 characters or less")
    private String descriptionEn;

    @NotBlank(message = "Arabic Description is required")
    @Size(max = 255, message = "Arabic description must be 255 characters or less")
    private String descriptionAr;

    @NotNull(message = "Status is required")
    private Boolean isActive;
}
