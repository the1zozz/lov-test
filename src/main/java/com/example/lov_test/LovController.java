package com.example.lov_test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lov")
public class LovController {

    private final LovService lovService;

    public LovController(LovService lovService) {
        this.lovService = lovService;
    }
    @GetMapping
    public ResponseEntity<List<LovDto>> getLov(@RequestParam(required = false) String lovCode) {
        return new ResponseEntity<>(lovService.getLov(lovCode) , HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<LovDto> createLov(@RequestBody LovDto lovDto) {
        return ResponseEntity.ok(lovService.createLov(lovDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<LovDto> updateLov(@PathVariable long id , @RequestBody LovDto lovDto) {
        return ResponseEntity.ok(lovService.updateLov(id ,lovDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<LovDto> deleteLov(@PathVariable long id) {
        return ResponseEntity.ok(lovService.deleteLov(id));
    }
    @GetMapping("/groups")
    public ResponseEntity<List<String>> getDistinctLovCode() {
        return ResponseEntity.ok(lovService.getDistinctLovCode());
    }




}
