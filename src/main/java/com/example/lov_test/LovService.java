package com.example.lov_test;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.lov_test.LovMapper.*;

@Service
public class LovService {
    @Autowired
    private  LovRepository lovRepository;
<<<<<<< HEAD
    @Cacheable(value = "lov")
    public List<LovDto> getLov(String lovCode) {
=======

    @Cacheable(value = "lov")
    public List<LovResponse> getLov(String lovCode , String lang) {
>>>>>>> 27f9e0d (add arabic description)
        List<ListOfValues> lovList = (lovCode!=null)
                ? lovRepository.findByLovCode(lovCode)
                : lovRepository.findAll();
        return LovMapper.lovDtoList(lovList , lang);
    }
    @CacheEvict(value = "lov", allEntries = true)
<<<<<<< HEAD
    public  LovDto createLov(LovDto lovDto) {
        ListOfValues lov = toEntity(lovDto);
=======
    public LovRequest createLov(LovRequest lovRequest) {
        ListOfValues lov = toEntity(lovRequest);
>>>>>>> 27f9e0d (add arabic description)
        lov = lovRepository.save(lov);
        return toRequestDto(lov);
    }
    @CacheEvict(value = "lov", allEntries = true)
<<<<<<< HEAD
    public LovDto updateLov(long id ,LovDto lovDto) {
=======
    public LovRequest updateLov(long id , LovRequest lovRequest) {
>>>>>>> 27f9e0d (add arabic description)
        ListOfValues lov = lovRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LOV not found"));
        lov.setLovCode(lovRequest.getLovCode());
        lov.setLovValue(lovRequest.getLovValue());
        lov.setDescriptionEn(lovRequest.getDescriptionEn());
        lov.setDescriptionAr(lovRequest.getDescriptionAr());
        lov.setIsActive(lovRequest.getIsActive());
        lov = lovRepository.save(lov);
        return toRequestDto(lov);

    }
    @CacheEvict(value = "lov", allEntries = true)
<<<<<<< HEAD
    public LovDto deleteLov(long id) {
        ListOfValues lov = lovRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LOV not found"));
        lovRepository.delete(lov);
        return toDto(lov);
=======
    public String deleteLov(long id) {
        ListOfValues lov = lovRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LOV not found"));
        lovRepository.delete(lov);
        return "LOV deleted successfully";
>>>>>>> 27f9e0d (add arabic description)
    }
    public List<String> getDistinctLovCode() {
        return lovRepository.findDistinctLovCode();
    }



<<<<<<< HEAD
    // Mappers
    private LovDto toDto(ListOfValues lov) {
        LovDto lovDto = new LovDto();
        BeanUtils.copyProperties(lov, lovDto);
        return lovDto;
    }

    private ListOfValues toEntity(LovDto lovDto) {
        ListOfValues lov = new ListOfValues();
        BeanUtils.copyProperties(lovDto, lov);
        return lov;
    }
=======

>>>>>>> 27f9e0d (add arabic description)



}
