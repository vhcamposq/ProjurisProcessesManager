package com.vhcamposq.projurisprocessesmanager.controller;

import com.vhcamposq.projurisprocessesmanager.dto.MessageResponseDTO;
import com.vhcamposq.projurisprocessesmanager.dto.ProcessesDTO;
import com.vhcamposq.projurisprocessesmanager.service.ProcessesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/processes")
public class ProcessesController {
    private final ProcessesService processesService;

    public ProcessesController(ProcessesService processesService) {
        this.processesService = processesService;
    }

    @PostMapping
    public MessageResponseDTO create(@RequestBody @Valid ProcessesDTO processesDTO){
        return processesService.create(processesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessesDTO> findById(@PathVariable Long id) {
        ProcessesDTO processesDTO = processesService.findById(id);
        return ResponseEntity.ok(processesDTO);
    }

    @GetMapping
    public List<ProcessesDTO> findAll() {
        return processesService.findAll();
    }

    @PutMapping("/{id}")
    public MessageResponseDTO update(@PathVariable Long id, @RequestBody @Valid ProcessesDTO processesDTO) {
        return processesService.update(id, processesDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        processesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
