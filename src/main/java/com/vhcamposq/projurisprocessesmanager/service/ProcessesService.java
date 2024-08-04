package com.vhcamposq.projurisprocessesmanager.service;

import com.vhcamposq.projurisprocessesmanager.dto.MessageResponseDTO;
import com.vhcamposq.projurisprocessesmanager.entity.Processes;
import com.vhcamposq.projurisprocessesmanager.repository.ProcessesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class ProcessesService {

    private ProcessesRepository processesRepository;

    @Autowired
    public ProcessesService(ProcessesRepository processesRepository) {
        this.processesRepository = processesRepository;
    }

    public MessageResponseDTO create (Processes processes){
        Processes savedProcesses = processesRepository.save(processes);
        return  MessageResponseDTO.builder().message("Process created with ID " + savedProcesses.getId()).build();

    }
}
