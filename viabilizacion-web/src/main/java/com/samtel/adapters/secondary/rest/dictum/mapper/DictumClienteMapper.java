package com.samtel.adapters.secondary.rest.dictum.mapper;

import com.samtel.domain.solicitud.Cliente;
import com.samtel.domain.solicitud.dictum.RequestDictum;

public interface DictumClienteMapper {

    RequestDictum toRequestDictum(Cliente cliente);
}
