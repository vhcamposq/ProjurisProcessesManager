package com.vhcamposq.projurisprocessesmanager.mapper;

import com.vhcamposq.projurisprocessesmanager.dto.ProcessesDTO;
import com.vhcamposq.projurisprocessesmanager.entity.Processes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProcessesMapper {

    ProcessesMapper INSTANCE = Mappers.getMapper(ProcessesMapper.class);

    Processes toModel(ProcessesDTO processesDTO);

    ProcessesDTO toDTO(Processes processes);
}
