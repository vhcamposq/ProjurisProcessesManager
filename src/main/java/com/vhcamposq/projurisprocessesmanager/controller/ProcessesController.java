package com.vhcamposq.projurisprocessesmanager.controller;

import com.vhcamposq.projurisprocessesmanager.dto.MessageResponseDTO;
import com.vhcamposq.projurisprocessesmanager.dto.ProcessesDTO;
import com.vhcamposq.projurisprocessesmanager.service.ProcessesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller para gerenciar processos.
 * Responsável por expor as APIs para operações CRUD de processos.
 */
@RestController
@RequestMapping("/api/v1/processes")
public class ProcessesController {
    private final ProcessesService processesService;

    public ProcessesController(ProcessesService processesService) {
        this.processesService = processesService;
    }

    /**
     * Cria um novo processo.
     *
     * @param processesDTO DTO do processo a ser criado.
     * @return Mensagem de resposta com o ID do processo criado.
     */
    @PostMapping
    public MessageResponseDTO create(@RequestBody @Valid ProcessesDTO processesDTO){
        return processesService.create(processesDTO);
    }

    /**
     * Busca um processo pelo ID.
     *
     * @param id ID do processo a ser buscado.
     * @return ResponseEntity contendo o DTO do processo encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProcessesDTO> findById(@PathVariable Long id) {
        ProcessesDTO processesDTO = processesService.findById(id);
        return ResponseEntity.ok(processesDTO);
    }

    /**
     * Busca todos os processos.
     *
     * @return Lista de DTOs de todos os processos.
     */
    @GetMapping
    public List<ProcessesDTO> findAll() {
        return processesService.findAll();
    }

    /**
     * Atualiza um processo existente.
     *
     * @param id ID do processo a ser atualizado.
     * @param processesDTO DTO do processo com os novos dados.
     * @return Mensagem de resposta com o ID do processo atualizado.
     */
    @PutMapping("/{id}")
    public MessageResponseDTO update(@PathVariable Long id, @RequestBody @Valid ProcessesDTO processesDTO) {
        return processesService.update(id, processesDTO);
    }

    /**
     * Deleta um processo pelo ID.
     *
     * @param id ID do processo a ser deletado.
     * @return ResponseEntity sem conteúdo.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        processesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
