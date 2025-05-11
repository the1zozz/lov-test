package com.example.lov_test;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/lov")
public class LovController {

    private final LovService lovService;


    @GetMapping
    public ResponseEntity<List<LovResponse>> getLov(@RequestParam(required = false) String lovCode) {
        return new ResponseEntity<>(lovService.getLov(lovCode) , HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<LovRequest> createLov(@Valid @RequestBody LovRequest lovRequest) {
        return ResponseEntity.ok(lovService.createLov(lovRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<LovRequest> updateLov(@PathVariable long id ,@Valid @RequestBody LovRequest lovRequest) {
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
