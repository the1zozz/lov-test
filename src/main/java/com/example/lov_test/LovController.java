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
    public ResponseEntity<List<LovResponse>> getLov(@RequestParam(required = false) String lovCode,
                                                    @RequestParam(defaultValue = "en") String lang) {
        return new ResponseEntity<>(lovService.getLov(lovCode , lang) , HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<LovRequest> createLov(@RequestBody LovRequest lovRequest) {
        return ResponseEntity.ok(lovService.createLov(lovRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<LovRequest> updateLov(@PathVariable long id , @RequestBody LovRequest lovRequest) {
        return ResponseEntity.ok(lovService.updateLov(id , lovRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLov(@PathVariable long id) {
        return ResponseEntity.ok(lovService.deleteLov(id));
    }
    @GetMapping("/groups")
    public ResponseEntity<List<String>> getDistinctLovCode() {
        return ResponseEntity.ok(lovService.getDistinctLovCode());
    }




}
