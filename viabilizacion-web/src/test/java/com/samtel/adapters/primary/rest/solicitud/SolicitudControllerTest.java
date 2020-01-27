package com.samtel.adapters.primary.rest.solicitud;

import com.samtel.domain.solicitud.Cliente;
import com.samtel.ports.primary.solicitud.SolicitudService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolicitudControllerTest {
    public static final String EXPECTED_RESULT = "RESULT";
    private SolicitudController solicitudController;

    @Mock
    private SolicitudService solicitudService;
    @Mock
    private ClientePayLoad clientePayLoad;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private Cliente cliente;

    @Before
    public void setup() {
        solicitudController = new SolicitudController(solicitudService, modelMapper);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    //TODO fix test
    public void testCumplimientoSolicitudSuccess() {
        Mockito.when(modelMapper.map(clientePayLoad, Cliente.class)).thenReturn(cliente);
        Mockito.when(solicitudService.cumplimientoSolicitud(cliente)).thenReturn(EXPECTED_RESULT);
        String result = solicitudController.solicitud(clientePayLoad);
        Assert.assertNull(result);
    }

}
