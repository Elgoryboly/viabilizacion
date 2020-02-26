package co.com.santander.core.flow.impl;

import co.com.santander.adapters.secondary.rest.dictum.mapper.DictumClienteMapper;
import co.com.santander.core.flow.ValidateRequest;
import co.com.santander.core.response.ResponseFlow;
import co.com.santander.domain.solicitud.Cliente;
import co.com.santander.ports.secondary.solicitud.DictumService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("searchDictum")
public class SearchDictumImpl implements ValidateRequest {

    private static final Logger log = LoggerFactory.getLogger(SearchDictumImpl.class);

    private ValidateRequest validateRequest;
    @Getter
    @Setter
    private Cliente cliente;
    @Getter
    @Setter
    private String idRequest;

    private DictumService dictumService;
    private DictumClienteMapper dictumClienteMapper;

    @Autowired
    public SearchDictumImpl(@Qualifier("proxyLogSearchReconocer") ValidateRequest validateRequest, DictumService dictumService, DictumClienteMapper dictumClienteMapper) {
        super();
        this.validateRequest = validateRequest;
        this.dictumService = dictumService;
        this.dictumClienteMapper = dictumClienteMapper;
    }


    @Override
    public Optional<ResponseFlow> process(Cliente cliente, String idRequest) {
        setCliente(cliente);
        setIdRequest(idRequest);
        llamarSevicio();
        return validateRequest.process(getCliente(), idRequest);
    }

    public void llamarSevicio() {
        Optional<String> respuesta = dictumService.consultarSolicitudDictum(dictumClienteMapper.toRequestDictum(getCliente()), getIdRequest());
        if (respuesta.isPresent())
            log.info("Esta es la respuesta del servicio: {}", respuesta.get());
    }

}