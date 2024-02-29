package org.denizkpln.townservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.denizkpln.townservice.dto.TownDto;
import org.denizkpln.townservice.service.TownService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/town")
@Slf4j
public class TownController {
    private final TownService townService;

    public TownController(TownService townService) {
        this.townService = townService;
    }

    @PostMapping
    public ResponseEntity<TownDto> save(@RequestBody TownDto townDto){
        log.info("town save",townDto);
        return ResponseEntity.ok(townService.save(townDto));
    }

    @GetMapping("/findByName/{townname}")
    public ResponseEntity<List<TownDto>> findByName(@PathVariable String townname){
        log.info("findByName",townname);
        return ResponseEntity.ok(townService.findByName(townname));
    }

    @GetMapping("/existByName/{townname}")
    public ResponseEntity<Boolean> existByName(@PathVariable String townname){
        log.info("existByName",townname);
        return ResponseEntity.ok(townService.existByName(townname));
    }


    @GetMapping("/findbyid/{id}")
    public ResponseEntity<TownDto> findbyid(@PathVariable Long id){
        log.info("findbyid",id);
        return ResponseEntity.ok(townService.findbyid(id));
    }






}
