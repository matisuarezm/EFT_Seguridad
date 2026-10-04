package com.duoc.EFTSeguridad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

    @Test
    void testConstantsConstructorAndFields() {
        Constants constants = new Constants();
        assertNotNull(constants);

        // Verifica que la clase exista y cargue sus constantes publicas/estaticas
        assertNotNull(Constants.HEADER_AUTHORIZACION_KEY);
        assertNotNull(Constants.TOKEN_BEARER_PREFIX);
        assertNotNull(Constants.SUPER_SECRET_KEY);
    }
}