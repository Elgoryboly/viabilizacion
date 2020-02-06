package com.samtel.validation.service;

import com.samtel.validation.common.exception.BadRequestException;
import com.samtel.validation.entity.Ciudad;
import com.samtel.validation.service.impl.CiudadServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CiudadServiceTest {

    public static final String NOMBRE_CIUDAD = "BOGOTA";
    public static final String RESPUESTA_FALSE = "INACTIVO";
    public static final String RESPUESTA_TRUE = "ACTIVO";
    private CiudadService ciudadService;

    @Mock
    private CiudadRepository ciudadRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ciudadService = new CiudadServiceImpl(ciudadRepository);

    }

    @Test(expected = BadRequestException.class)
    public void testBuscaCodigoONombreErrorNull() {
        Mockito.when(ciudadRepository.buscarPorCodigoONombre(NOMBRE_CIUDAD)).thenReturn(null);
        Boolean respuesta = ciudadService.validarCiudad(NOMBRE_CIUDAD);
    }

    @Test
    public void testBuscaCodigoONombreSuccessFalse() {
        Ciudad ciudad = new Ciudad();
        ciudad.setEstado(RESPUESTA_FALSE);
        Mockito.when(ciudadRepository.buscarPorCodigoONombre(NOMBRE_CIUDAD)).thenReturn(ciudad);
        Boolean respuesta = ciudadService.validarCiudad(NOMBRE_CIUDAD);
        Assert.assertEquals(Boolean.FALSE, respuesta);
    }

    @Test
    public void testBuscaCodigoONombreSuccessTrue() {
        Ciudad ciudad = new Ciudad();
        ciudad.setEstado(RESPUESTA_TRUE);
        Mockito.when(ciudadRepository.buscarPorCodigoONombre(NOMBRE_CIUDAD)).thenReturn(ciudad);
        Boolean respuesta = ciudadService.validarCiudad(NOMBRE_CIUDAD);
        Assert.assertEquals(Boolean.TRUE, respuesta);
    }
}
