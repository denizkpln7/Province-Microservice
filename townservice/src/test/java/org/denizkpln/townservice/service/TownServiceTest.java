package org.denizkpln.townservice.service;

import org.denizkpln.townservice.dto.TownDto;
import org.denizkpln.townservice.model.Town;
import org.denizkpln.townservice.repository.TownRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TownServiceTest {


    private TownService townService;
    private  TownRepository townRepository;

    private  ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        townRepository= Mockito.mock(TownRepository.class);
        modelMapper=Mockito.mock(ModelMapper.class);

        townService=new TownService(townRepository,modelMapper);
    }


    @Test
    void testwhenTownSave(){
        TownDto dto=getTownDto();
        Town town=getTown();
        Mockito.when(modelMapper.map(dto,Town.class)).thenReturn(town);

        if (town !=null && town.getName() !=null  ){
            town.setName(town.getName().toLowerCase());
        }

        Mockito.when(townRepository.save(Mockito.any(Town.class))).thenReturn(town);
        TownDto result=townService.save(dto);
        assertEquals(dto,result);
        Mockito.verify(townRepository,Mockito.times(1)).save(Mockito.any(Town.class));

    }

    @Test
    void testWhenexistByName(){

        String name="beyoğlu";
        Mockito.when(townRepository.existsByName(name)).thenReturn(true);
        Boolean result=townService.existByName(name);
        assertEquals(true,result);

        Mockito.verify(townRepository,Mockito.times(1)).existsByName(name);
    }

    @Test
    void testfindbyid(){
        Long id=1L;
        TownDto dto=getTownDto();
        Town town=getTown();

        Mockito.when(townRepository.findById(1L)).thenReturn(Optional.of(town));
        TownDto result=townService.findbyid(1L);
        assertEquals(dto,result);
        Mockito.verify(townRepository,Mockito.times(1)).findById(1L);

    }

    private Town getTown() {
        Town town=new Town();
        town.setId(1L);
        town.setName("beyoğlu");
        return town;
    }

    private TownDto getTownDto() {
        TownDto townDto=new TownDto();
        townDto.setId(1L);
        townDto.setName("beyoğlu");
        return townDto;
    }
}