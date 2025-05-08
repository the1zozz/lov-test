package com.example.lov_test;

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
    private final LovConfig lovConfig;


    @GetMapping
    public ResponseEntity<List<LovResponse>> getLov(@RequestParam(required = false) String lovCode,
                                                    @RequestHeader(value = "accept-language" , required = false) String acceptLanguage) {
        String lang = resolveLanguage(acceptLanguage);
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

    // Helper Method
    public String resolveLanguage(String acceptLanguage) {
        if (acceptLanguage == null || acceptLanguage.isBlank() ) {
            return lovConfig.getDefaultLanguage();
        }
        String lang = acceptLanguage.toLowerCase();
        if (acceptLanguage.startsWith("ar"))  return "ar";
        if (acceptLanguage.startsWith("en"))  return "en";
        return lovConfig.getDefaultLanguage();
    }
}
