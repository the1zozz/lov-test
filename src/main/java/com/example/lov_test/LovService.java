package com.example.lov_test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.lov_test.LovMapper.*;
@RequiredArgsConstructor
@Service
public class LovService {

    private  LovRepository lovRepository;

    @Cacheable("lov")
    public List<LovResponse> getLov(String lovCode, String lang) {
        List<ListOfValues> lovList = (lovCode != null)
                ? lovRepository.findByLovCode(lovCode)
                : lovRepository.findAll() ;

        return lovDtoList(lovList , lang);
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
}
