package co.com.santander.ports.secondary.solicitud;

import co.com.santander.core.domain.solicitud.dictum.RequestDictum;

import java.util.Optional;

public interface DictumService {

    Optional<String> consultarSolicitudDictum(RequestDictum request,Long idRequest);
}
