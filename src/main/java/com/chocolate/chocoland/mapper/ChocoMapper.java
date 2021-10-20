package com.chocolate.chocoland.mapper;

import com.chocolate.chocoland.dto.ChocoDTO;
import com.chocolate.chocoland.entity.Choco;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChocoMapper {

    ChocoMapper INSTANCE = Mappers.getMapper(ChocoMapper.class);

    Choco toModel(ChocoDTO beerDTO);

    static ChocoDTO toDTO(Choco choco);
}
