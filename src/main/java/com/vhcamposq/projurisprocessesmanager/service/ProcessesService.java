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

/**
 * Serviço para operações CRUD de processos.
 */
@Service
public class ProcessesService {

    private ProcessesRepository processesRepository;

    private final ProcessesMapper processesMapper = ProcessesMapper.INSTANCE;

    @Autowired
    public ProcessesService(ProcessesRepository processesRepository) {
        this.processesRepository = processesRepository;
    }


    /**
     * Cria um novo processo.
     *
     * @param processesDTO DTO do processo a ser criado.
     * @return Mensagem de resposta com o ID do processo criado.
     */
    public MessageResponseDTO create(ProcessesDTO processesDTO) {
        Processes saveProcesses = processesMapper.toModel(processesDTO);

        Processes savedProcesses = processesRepository.save(saveProcesses);
        return MessageResponseDTO.builder()
                .message("Processes created with ID " + savedProcesses.getId())
                .build();
    }

    /**
     * Busca um processo pelo ID.
     *
     * @param id ID do processo a ser buscado.
     * @return DTO do processo encontrado.
     */
    public ProcessesDTO findById(Long id) {
        Processes processes = processesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processes not found"));
        return processesMapper.toDTO(processes);
    }

    /**
     * Busca todos os processos.
     *
     * @return Lista de DTOs de todos os processos.
     */
    public List<ProcessesDTO> findAll() {
        return processesRepository.findAll()
                .stream()
                .map(processesMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Atualiza um processo existente.
     *
     * @param id ID do processo a ser atualizado.
     * @param processesDTO DTO do processo com os novos dados.
     * @return Mensagem de resposta com o ID do processo atualizado.
     */
    public MessageResponseDTO update(Long id, ProcessesDTO processesDTO) {
        processesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processes not found"));

        Processes processesToUpdate = processesMapper.toModel(processesDTO);
        Processes updatedProcesses = processesRepository.save(processesToUpdate);
        return createMessageResponse(updatedProcesses.getId(), "Processes updated with ID ");
    }

    /**
     * Deleta um processo pelo ID.
     *
     * @param id ID do processo a ser deletado.
     */
    public void delete(Long id) {
        processesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processes not found"));
        processesRepository.deleteById(id);
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }
}
