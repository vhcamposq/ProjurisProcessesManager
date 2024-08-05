package com.vhcamposq.projurisprocessesmanager.controller;

import com.vhcamposq.projurisprocessesmanager.dto.LawyerDTO;
import com.vhcamposq.projurisprocessesmanager.dto.MessageResponseDTO;
import com.vhcamposq.projurisprocessesmanager.dto.ProcessesDTO;
import com.vhcamposq.projurisprocessesmanager.service.ProcessesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.vhcamposq.projurisprocessesmanager.utils.TestUtils.asJsonString;

class ProcessesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProcessesService processesService;

    @InjectMocks
    private ProcessesController processesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(processesController).build();
    }

    @Test
    void testCreate() throws Exception {
        ProcessesDTO processesDTO = createProcessesDTO();
        MessageResponseDTO responseDTO = createMessageResponseDTO("Processes created with ID 1");

        when(processesService.create(any(ProcessesDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/v1/processes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(processesDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Processes created with ID 1"));
    }


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

    @Test
    void testFindById() throws Exception {
        ProcessesDTO processesDTO = createProcessesDTO();

        when(processesService.findById(1L)).thenReturn(processesDTO);

        mockMvc.perform(get("/api/v1/processes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(processesDTO.getName()))
                .andExpect(jsonPath("$.caseNumber").value(processesDTO.getCaseNumber()))
                .andExpect(jsonPath("$.status").value(processesDTO.getStatus()))
                .andExpect(jsonPath("$.lawyer.name").value(processesDTO.getLawyer().getName()))
                .andExpect(jsonPath("$.lawyer.oabNumber").value(processesDTO.getLawyer().getOabNumber()));
    }

    @Test
    void testFindAll() throws Exception {
        ProcessesDTO processesDTO = createProcessesDTO();

        when(processesService.findAll()).thenReturn(Collections.singletonList(processesDTO));

        mockMvc.perform(get("/api/v1/processes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(processesDTO.getName()))
                .andExpect(jsonPath("$[0].caseNumber").value(processesDTO.getCaseNumber()))
                .andExpect(jsonPath("$[0].status").value(processesDTO.getStatus()))
                .andExpect(jsonPath("$[0].lawyer.name").value(processesDTO.getLawyer().getName()))
                .andExpect(jsonPath("$[0].lawyer.oabNumber").value(processesDTO.getLawyer().getOabNumber()));
    }
    private MessageResponseDTO createMessageResponseDTO(String message) {
        return MessageResponseDTO.builder()
                .message(message)
                .build();
    }
}
