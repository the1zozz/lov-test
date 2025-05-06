package com.example.lov_test;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LovService {
    @Autowired
    private  LovRepository lovRepository;

    public List<LovDto> getLov(String lovCode) {
        List<ListOfValues> lovList = (lovCode!=null)
                ? lovRepository.findByLovCode(lovCode)
                : lovRepository.findAll();
        return lovList.stream().map(this::toDto).toList();
    }

    public  LovDto createLov(LovDto lovDto) {
        ListOfValues lov = toEntity(lovDto);
        lov = lovRepository.save(lov);
        return toDto(lov);
    }

    public LovDto updateLov(long id ,LovDto lovDto) {
        ListOfValues lov = lovRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LOV not found"));
        lov.setLovCode(lovDto.getLovCode());
        lov.setLovValue(lovDto.getLovValue());
        lov.setDescription(lovDto.getDescription());
        lov.setIsActive(lovDto.getIsActive());
        lov = lovRepository.save(lov);
        return toDto(lov);

    }


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


    public LovDto deleteLov(long id) {
        ListOfValues lov = lovRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LOV not found"));
        lovRepository.delete(lov);
        return toDto(lov);
    }

    public List<String> getDistinctLovCode() {
        return lovRepository.findDistinctLovCode();
    }
}
