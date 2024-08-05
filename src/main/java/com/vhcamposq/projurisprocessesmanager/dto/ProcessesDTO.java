package com.vhcamposq.projurisprocessesmanager.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object para processos.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessesDTO {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String caseNumber;

    @NotBlank
    @Size(max = 100)
    private String status;

    @Valid
    @NotNull
    private LawyerDTO lawyer;
}
