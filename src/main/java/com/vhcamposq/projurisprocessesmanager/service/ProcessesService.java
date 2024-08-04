package com.vhcamposq.projurisprocessesmanager.service;

import com.vhcamposq.projurisprocessesmanager.dto.MessageResponseDTO;
import com.vhcamposq.projurisprocessesmanager.dto.ProcessesDTO;
import com.vhcamposq.projurisprocessesmanager.entity.Processes;
import com.vhcamposq.projurisprocessesmanager.mapper.ProcessesMapper;
import com.vhcamposq.projurisprocessesmanager.repository.ProcessesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public ProcessesDTO findById(Long id) {
        Processes processes = processesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Process not found"));
        return processesMapper.toDTO(processes);
    }

    public List<ProcessesDTO> findAll() {
        return processesRepository.findAll()
                .stream()
                .map(processesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO update(Long id, ProcessesDTO processesDTO) {
        processesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Process not found"));

        Processes processesToUpdate = processesMapper.toModel(processesDTO);
        Processes updatedProcesses = processesRepository.save(processesToUpdate);
        return createMessageResponse(updatedProcesses.getId(), "Process updated with ID ");
    }

    public void delete(Long id) {
        processesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Process not found"));
        processesRepository.deleteById(id);
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }
}
