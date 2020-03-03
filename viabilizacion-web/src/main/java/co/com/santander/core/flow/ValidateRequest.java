package co.com.santander.core.flow;

import co.com.santander.core.domain.solicitud.Cliente;
import co.com.santander.core.response.ResponseFlow;

import java.util.Optional;

public interface ValidateRequest {

    Optional<ResponseFlow> process(Cliente cliente, Long idRequest);
}
