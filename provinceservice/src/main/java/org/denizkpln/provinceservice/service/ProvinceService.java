package org.denizkpln.provinceservice.service;

import jakarta.transaction.Transactional;
import org.denizkpln.provinceservice.client.TownServiceClient;
import org.denizkpln.provinceservice.dto.ProvinceDto;
import org.denizkpln.provinceservice.dto.ProvinceRequestDto;
import org.denizkpln.provinceservice.dto.TownDto;
import org.denizkpln.provinceservice.exception.CustomException;
import org.denizkpln.provinceservice.model.Province;
import org.denizkpln.provinceservice.repository.ProvinceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper;
    private final TownServiceClient townServiceClient;

    public ProvinceService(ProvinceRepository provinceRepository, ModelMapper modelMapper, TownServiceClient townServiceClient) {
        this.provinceRepository = provinceRepository;
        this.modelMapper = modelMapper;
        this.townServiceClient = townServiceClient;
    }

    @Transactional()
    public void save(ProvinceRequestDto provinceRequestDto){

        List<TownDto> townListDto=new ArrayList<>();

        Province province=provinceRepository.findByName(provinceRequestDto.getProvinceName());

        if (province !=null && province.getTownIdList().size()>0){
            if (province.getTownIdList().size()>=3){
                throw new CustomException("Bu il için geçerli sayıda ilçe kaydedilmiştir","exsist",400);
            }
        }

        Boolean isexist=townServiceClient.existByName(provinceRequestDto.getTownName()).getBody();
        if (isexist){
            List<TownDto> list=townServiceClient.findByName(provinceRequestDto.getTownName()).getBody();
            townListDto.addAll(list);
        }

        if (province !=null && province.getId()!=null && isexist){
            if (townListDto.size()>0){
                for (TownDto dto:townListDto){
                    if (province.getTownIdList().contains(dto.getId())){
                      throw new CustomException("Böyle bir kayıt mevcut","exsist",400);
                    }
                }
            }
        }

        TownDto saveTown=townServiceClient.save(new TownDto().builder().name(provinceRequestDto.getTownName()).population(provinceRequestDto.getPopulation()).build()).getBody();


        List<Long> ids=new ArrayList<>();
        if (province !=null && province.getId()!=null){
            ids=province.getTownIdList();
            ids.add(saveTown.getId());
            province.setName(province.getName().substring(0).toUpperCase()+province.getName().substring(1));
            provinceRepository.save(province);
        }else {
            ids.add(saveTown.getId());
            Province provinceSave=new Province().builder().townIdList(ids).name(provinceRequestDto.getProvinceName()).build();
            provinceSave.setName(provinceRequestDto.getProvinceName().substring(0,1).toUpperCase()+provinceRequestDto.getProvinceName().substring(1));
            provinceRepository.save(provinceSave);
        }
    }

    public List<ProvinceDto> findAll() {
        List<Province> list=provinceRepository.findAll();
        List<ProvinceDto> dtos=new ArrayList<>();

        list.forEach(item->{
            List<TownDto> townDtoList=item.getTownIdList().stream()
                    .map(itemTown->townServiceClient.findbyid(itemTown).getBody())
                    .collect(Collectors.toList());
            ProvinceDto newDto=new ProvinceDto().builder()
                    .id(item.getId())
                    .name(item.getName())
                    .townDtoList(townDtoList)
                    .build();
            dtos.add(newDto);
        });

        return dtos;
    }


}
