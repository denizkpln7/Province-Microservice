package org.denizkpln.provinceservice.dto;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProvinceDto {

    private Long id;

    private String name;

    List<TownDto> townDtoList;

}
