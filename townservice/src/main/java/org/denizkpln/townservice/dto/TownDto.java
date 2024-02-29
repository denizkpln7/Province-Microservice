package org.denizkpln.townservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class TownDto {

    private Long id;

    private String name;

    private Long population;
}
