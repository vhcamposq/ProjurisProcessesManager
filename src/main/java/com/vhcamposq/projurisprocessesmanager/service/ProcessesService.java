package com.vhcamposq.projurisprocessesmanager.service;

import com.vhcamposq.projurisprocessesmanager.dto.MessageResponseDTO;
import com.vhcamposq.projurisprocessesmanager.dto.ProcessesDTO;
import com.vhcamposq.projurisprocessesmanager.entity.Processes;
import com.vhcamposq.projurisprocessesmanager.mapper.ProcessesMapper;
import com.vhcamposq.projurisprocessesmanager.repository.ProcessesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class ProcessesService {

    private ProcessesRepository processesRepository;

    private final ProcessesMapper processesMapper = ProcessesMapper.INSTANCE;

    @Autowired
    public ProcessesService(ProcessesRepository processesRepository) {
        this.processesRepository = processesRepository;
    }

    public MessageResponseDTO create (ProcessesDTO processesDTO){
        Processes saveProcesses = processesMapper.toModel(processesDTO);

        Processes savedProcesses = processesRepository.save(saveProcesses);
        return  MessageResponseDTO.builder().message("Process created with ID " + savedProcesses.getId()).build();

    }
}
