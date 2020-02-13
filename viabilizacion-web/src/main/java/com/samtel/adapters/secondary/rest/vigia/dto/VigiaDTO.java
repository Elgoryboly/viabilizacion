package com.samtel.adapters.secondary.rest.vigia.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VigiaDTO {
    private String codigoEjecucion;
    private MensajeDTO mensaje;
}