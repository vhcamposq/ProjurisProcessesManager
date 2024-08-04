package com.vhcamposq.projurisprocessesmanager.controller;

import com.vhcamposq.projurisprocessesmanager.dto.MessageResponseDTO;
import com.vhcamposq.projurisprocessesmanager.entity.Processes;
import com.vhcamposq.projurisprocessesmanager.repository.ProcessesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/processes")
public class ProcessesController {

    private ProcessesRepository processesRepository;
    @Autowired
    public ProcessesController(ProcessesRepository processesRepository) {
        this.processesRepository = processesRepository;
    }
    @PostMapping
    public MessageResponseDTO create (@RequestBody Processes processes){
        Processes savedProcesses = processesRepository.save(processes);
        return  MessageResponseDTO.builder().message("Process created with ID " + savedProcesses.getId()).build();

    }
}
