package com.vhcamposq.projurisprocessesmanager.controller;

import com.vhcamposq.projurisprocessesmanager.dto.MessageResponseDTO;
import com.vhcamposq.projurisprocessesmanager.dto.ProcessesDTO;
import com.vhcamposq.projurisprocessesmanager.entity.Processes;
import com.vhcamposq.projurisprocessesmanager.repository.ProcessesRepository;
import com.vhcamposq.projurisprocessesmanager.service.ProcessesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/processes")
public class ProcessesController {

    private ProcessesService processesService;
    @Autowired
    public ProcessesController(ProcessesService processesService) {
        this.processesService = processesService;
    }

    @PostMapping
    public MessageResponseDTO create (@RequestBody @Valid ProcessesDTO processesDTO){
        return processesService.create(processesDTO);

    }
}
