package org.denizkpln.provinceservice.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.denizkpln.provinceservice.dto.ProvinceDto;
import org.denizkpln.provinceservice.dto.ProvinceRequestDto;
import org.denizkpln.provinceservice.service.ProvinceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/province")
@Slf4j
@Validated
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ProvinceRequestDto provinceRequestDto){
        log.info("province save");
        provinceService.save(provinceRequestDto);
        return ResponseEntity.ok("kaydetme işlemi başarılı");
    }

    @GetMapping("/findall")
    public ResponseEntity<List<ProvinceDto>> findAll(){
        log.info("findall");
        return ResponseEntity.ok(provinceService.findAll());
    }


}
