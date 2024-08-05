package com.vhcamposq.projurisprocessesmanager.service;

import com.vhcamposq.projurisprocessesmanager.dto.LawyerDTO;
import com.vhcamposq.projurisprocessesmanager.dto.ProcessesDTO;
import com.vhcamposq.projurisprocessesmanager.dto.MessageResponseDTO;
import com.vhcamposq.projurisprocessesmanager.entity.Lawyer;
import com.vhcamposq.projurisprocessesmanager.entity.Processes;
import com.vhcamposq.projurisprocessesmanager.mapper.ProcessesMapper;
import com.vhcamposq.projurisprocessesmanager.repository.ProcessesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProcessesServiceTest {

    @Mock
    private ProcessesRepository processesRepository;

    @InjectMocks
    private ProcessesService processesService;

    @Mock
    private ProcessesMapper processesMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Testa a criação de um novo processo
    @Test
    void testCreate() {
        ProcessesDTO processesDTO = createProcessesDTO();
        Processes processes = createProcesses();

        when(processesMapper.toModel(any(ProcessesDTO.class))).thenReturn(processes);
        when(processesRepository.save(any(Processes.class))).thenReturn(processes);

        MessageResponseDTO responseDTO = processesService.create(processesDTO);

        assertEquals("Processes created with ID 1", responseDTO.getMessage());
    }

    // Cria um objeto ProcessesDTO para ser utilizado nos testes
    private ProcessesDTO createProcessesDTO() {
        return ProcessesDTO.builder()
                .id(1L)
                .name("Processo tal")
                .caseNumber("123456789")
                .status("Em andamento")
                .lawyer(LawyerDTO.builder()
                        .id(1L)
                        .name("Machado de Assis")
                        .oabNumber("123456")
                        .build())
                .build();
    }

    // Cria um objeto Processes para ser utilizado nos testes
    private Processes createProcesses() {
        return Processes.builder()
                .id(1L)
                .name("Processo tal")
                .caseNumber("123456789")
                .status("Em andamento")
                .lawyer(Lawyer.builder()
                        .id(1L)
                        .name("Machado de Assis")
                        .experienceYears(30)
                        .oabNumber("123456")
                        .build())
                .build();
    }

    // Testa a busca de um processo pelo ID
    @Test
    void testFindById() {
        Processes processes = createProcesses();

        when(processesRepository.findById(anyLong())).thenReturn(Optional.of(processes));
        when(processesMapper.toDTO(any(Processes.class))).thenReturn(createProcessesDTO());

        ProcessesDTO processesDTO = processesService.findById(1L);

        assertEquals("Processo tal", processesDTO.getName());
    }

    // Testa a busca de um processo pelo ID quando o processo não é encontrado
    @Test
    void testFindById_NotFound() {
        when(processesRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> processesService.findById(1L));
    }

    // Testa a atualização de um processo existente
    @Test
    void testUpdate() {
        ProcessesDTO processesDTO = createProcessesDTO();
        Processes processes = createProcesses();

        when(processesRepository.findById(anyLong())).thenReturn(Optional.of(processes));
        when(processesMapper.toModel(any(ProcessesDTO.class))).thenReturn(processes);
        when(processesRepository.save(any(Processes.class))).thenReturn(processes);

        MessageResponseDTO responseDTO = processesService.update(1L, processesDTO);

        assertEquals("Processes updated with ID 1", responseDTO.getMessage());
    }

    // Testa a atualização de um processo inexistente
    @Test
    void testUpdate_NotFound() {
        ProcessesDTO processesDTO = createProcessesDTO();

        when(processesRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> processesService.update(1L, processesDTO));
    }

    // Testa a deleção de um processo existente
    @Test
    void testDelete() {
        Processes processes = createProcesses();

        when(processesRepository.findById(anyLong())).thenReturn(Optional.of(processes));
        doNothing().when(processesRepository).deleteById(anyLong());

        processesService.delete(1L);

        verify(processesRepository, times(1)).deleteById(anyLong());
    }

    // Testa a deleção de um processo inexistente
    @Test
    void testDelete_NotFound() {
        when(processesRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> processesService.delete(1L));
    }

}
