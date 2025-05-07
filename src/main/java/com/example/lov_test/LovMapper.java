package com.example.lov_test;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class LovMapper {
    // Map entity to ResponseDto according to lang
    public static LovResponse toDto(ListOfValues lov , String lang) {
        String description = "ar".equalsIgnoreCase(lang)
                ? lov.getDescriptionAr()
                : lov.getDescriptionEn();

        return LovResponse.builder()
                .id(lov.getId())
                .description(description)
                .lovValue(lov.getLovValue())
                .lovCode(lov.getLovCode())
                .isActive(lov.getIsActive())
                .build() ;
    }
    // Map list of entities to list of ResponseDto
    public static List<LovResponse> lovDtoList(List<ListOfValues> lovList , String lang) {
        return lovList.stream()
                .filter(ListOfValues::getIsActive)
                .map(lov -> toDto(lov , lang))
                .collect(Collectors.toList());
    }
    // Map RequestDto to entity
    public static ListOfValues toEntity(LovRequest lovRequest) {
        ListOfValues lov = new ListOfValues();
        lov.setLovValue(lovRequest.getLovValue());
        lov.setLovCode(lovRequest.getLovCode());
        lov.setDescriptionAr(lovRequest.getDescriptionAr());
        lov.setDescriptionEn(lovRequest.getDescriptionEn());
        lov.setIsActive(lovRequest.getIsActive());
        return lov;
    }
    // Map entity to RequestDto
    public static LovRequest toRequestDto(ListOfValues lov) {
        LovRequest lovRequest = new LovRequest();
        lovRequest.setLovValue(lov.getLovValue());
        lovRequest.setLovCode(lov.getLovCode());
        lovRequest.setDescriptionAr(lov.getDescriptionAr());
        lovRequest.setDescriptionEn(lov.getDescriptionEn());
        lovRequest.setIsActive(lov.getIsActive());
        return lovRequest;
    }
}
