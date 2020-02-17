package com.samtel.adapters.secondary.rest.common.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VigiaProperties {
    private String uriVigia;
    private String codigoEjecucion;
    private String origen;
    private String porcentaje;
}
