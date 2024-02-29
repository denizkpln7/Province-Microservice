package org.denizkpln.provinceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.denizkpln.provinceservice.validation.ProvinceValue;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProvinceRequestDto {

    private Long id;

    @ProvinceValue
    private String provinceName;

    @ProvinceValue
    private String townName;

    private Long population;
}
