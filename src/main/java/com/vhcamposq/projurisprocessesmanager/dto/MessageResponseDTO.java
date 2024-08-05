package com.vhcamposq.projurisprocessesmanager.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object para mensagens de resposta.
 */
@Data
@Builder
public class MessageResponseDTO {
    private String message;
}
