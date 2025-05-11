package com.example.lov_test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LovService {

    private final LovRepository lovRepository;

    @Cacheable("lov")
    public List<LovResponse> getLov(String lovCode) {
        List<ListOfValues> lovList = (lovCode != null)
                ? lovRepository.findByLovCode(lovCode)
                : lovRepository.findAll() ;

        return lovList.stream()
                .filter(ListOfValues::getIsActive)
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    @CacheEvict(value = "lov" , allEntries = true)
    public LovRequest createLov(LovRequest lovRequest) {
        ListOfValues lov = toEntity(lovRequest);
        return toRequestDto(lovRepository.save(lov));
    }
    @CacheEvict(value = "lov" , allEntries = true)
    public LovRequest updateLov(long id, LovRequest lovRequest) {
        ListOfValues existingLov = lovRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LOV not found"));
        existingLov.setLovValue(lovRequest.getLovValue());
        existingLov.setLovCode(lovRequest.getLovCode());
        existingLov.setDescriptionAr(lovRequest.getDescriptionAr());
        existingLov.setDescriptionEn(lovRequest.getDescriptionEn());
        existingLov.setIsActive(lovRequest.getIsActive());
        return toRequestDto(lovRepository.save(existingLov));
    }
    @CacheEvict(value = "lov" , allEntries = true)
    public String deleteLov(long id) {
        ListOfValues lov = lovRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LOV not found"));
        lovRepository.delete(lov);
        return "LOV deleted successfully";
    }

    public List<String> getDistinctLovCode() {
        return lovRepository.findDistinctLovCode();
    }

    @CacheEvict(value = "lov" , allEntries = true)
    public void clearLovCache() {
    }


    private ListOfValues toEntity(LovRequest lovRequest) {
        ListOfValues lov = new ListOfValues();
        BeanUtils.copyProperties(lovRequest, lov);
        return lov;
    }
    private LovRequest toRequestDto(ListOfValues lov) {
        LovRequest lovRequest = new LovRequest();
        BeanUtils.copyProperties(lov, lovRequest);
        return lovRequest;
    }
    private ListOfValues toEntity(LovResponse lovResponse) {
        ListOfValues lov = new ListOfValues();
        BeanUtils.copyProperties(lovResponse, lov);
        return lov;
    }
    private LovResponse toResponseDto(ListOfValues lov) {
        LovResponse lovResponse = new LovResponse();
        BeanUtils.copyProperties(lov, lovResponse);
        return lovResponse;
    }
}
