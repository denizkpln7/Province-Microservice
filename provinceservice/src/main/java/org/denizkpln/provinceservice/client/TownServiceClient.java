package org.denizkpln.provinceservice.client;

import org.denizkpln.provinceservice.dto.TownDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="town-service",path = "/town")
public interface TownServiceClient {

    @GetMapping("/findByName/{townname}")
    ResponseEntity<List<TownDto>> findByName(@PathVariable String townname);

    @PostMapping
    ResponseEntity<TownDto> save(@RequestBody TownDto townDto);

    @GetMapping("/existByName/{townname}")
    ResponseEntity<Boolean> existByName(@PathVariable String townname);

    @GetMapping("/findbyid/{id}")
    ResponseEntity<TownDto> findbyid(@PathVariable Long id);


}
