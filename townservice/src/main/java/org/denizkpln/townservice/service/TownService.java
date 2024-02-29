package org.denizkpln.townservice.service;

import org.denizkpln.townservice.dto.TownDto;
import org.denizkpln.townservice.exception.CustomException;
import org.denizkpln.townservice.model.Town;
import org.denizkpln.townservice.repository.TownRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TownService {

    private final TownRepository townRepository;

    private final ModelMapper modelMapper;

    public TownService(TownRepository townRepository, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
    }

    public TownDto save(TownDto dto){
        Town town=modelMapper.map(dto,Town.class);

        if (town !=null && town.getName() !=null){
            town.setName(town.getName().toLowerCase());
        }

        town=townRepository.save(town);
        //return modelMapper.map(town,TownDto.class);
        TownDto townDto=new TownDto().builder().id(town.getId()).name(town.getName()).build();
        return townDto;
    }

    public List<TownDto> findByName(String name){
        List<Town> townList=townRepository.findByName(name.toLowerCase());
        if (townList.size()<1){
            return null;
        }
        return townList.stream().map(item->modelMapper.map(item,TownDto.class)).collect(Collectors.toList());
    }


    public Boolean existByName(String townname) {
        return townRepository.existsByName(townname.toLowerCase());
    }

    public TownDto findbyid(Long id) {
        TownDto townDto=new TownDto();
       Optional<Town> town= townRepository.findById(id);

       if (town.isPresent()){
           townDto.setName(town.get().getName());
           townDto.setId(town.get().getId());
           townDto.setPopulation(town.get().getPopulation());
       }

       return townDto;
    }


}
