package org.denizkpln.provinceservice.service;

import org.denizkpln.provinceservice.client.TownServiceClient;
import org.denizkpln.provinceservice.dto.ProvinceDto;
import org.denizkpln.provinceservice.dto.ProvinceRequestDto;
import org.denizkpln.provinceservice.dto.TownDto;
import org.denizkpln.provinceservice.model.Province;
import org.denizkpln.provinceservice.repository.ProvinceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProvinceServiceTest {

    private ProvinceService provinceService;

    private  ProvinceRepository provinceRepository;
    private  ModelMapper modelMapper;
    private  TownServiceClient townServiceClient;

    @BeforeEach
    void setUp() {
        provinceRepository= Mockito.mock(ProvinceRepository.class);
        modelMapper=Mockito.mock(ModelMapper.class);
        townServiceClient=Mockito.mock(TownServiceClient.class);
        provinceService=new ProvinceService(provinceRepository,modelMapper,townServiceClient);
    }


    @Test
    void testwhenProvinceSave(){
        String name=getprovinceRequestDto().getProvinceName();
        String townName=getprovinceRequestDto().getTownName();
        ProvinceRequestDto provinceRequestDto=getprovinceRequestDto();
        List<Province> list=new ArrayList<>();
        List<Long> townIds =new ArrayList<>();
        townIds.add(1L);
        Province p1=new Province(1L,name,townIds);
        list.add(p1);

        TownDto townDto=new TownDto(2L,townName);

        Mockito.when(provinceRepository.findByName(name)).thenReturn(p1);
        Mockito.when(townServiceClient.existByName(townName)).thenReturn(ResponseEntity.ok(false));
        Mockito.when(townServiceClient.save(Mockito.any(TownDto.class))).thenReturn(ResponseEntity.ok(townDto));

        townIds.add(townDto.getId());
        p1.setTownIdList(townIds);

        Mockito.when(provinceRepository.save(p1)).thenReturn(p1);

        provinceService.save(provinceRequestDto);

        Mockito.verify(provinceRepository,Mockito.times(1)).findByName(name);
        Mockito.verify(townServiceClient,Mockito.times(1)).existByName(townName);
        Mockito.verify(townServiceClient,Mockito.times(1)).save(Mockito.any(TownDto.class));
        Mockito.verify(provinceRepository,Mockito.times(1)).save(p1);

    }



    @Test
    void testwhenProvincefindAll(){
        List<Province> list=new ArrayList<>();
        List<Long> townIds = Arrays.asList(1L);
        Province p1=new Province(1L,"istanbul",townIds);
        list.add(p1);

        List<ProvinceDto> dtos=new ArrayList<>();
        TownDto townDto=new TownDto(1L,"beyoglu");
        List<TownDto> townDtoList= Arrays.asList(townDto);
        ProvinceDto provinceDto=new ProvinceDto(1L,"istanbul",townDtoList);
        dtos.add(provinceDto);

        Mockito.when(provinceRepository.findAll()).thenReturn(list);
        Mockito.when(townServiceClient.findbyid(1L)).thenReturn(ResponseEntity.ok(townDto));

        List<ProvinceDto> result=provinceService.findAll();

        assertEquals(dtos,result);

    }

    private ProvinceRequestDto getprovinceRequestDto() {
        ProvinceRequestDto dto=new ProvinceRequestDto();
        dto.setProvinceName("istanbul");
        dto.setTownName("beyoglu");
        return dto;
    }


}