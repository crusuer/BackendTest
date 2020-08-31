package org.example.rest;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EndpointUrlsTest {
    @Test
    void deveLancarExcecaoAoCriarNovaInstanciaClasseUtilitaria() throws NoSuchMethodException {
        // QUANDO
        Constructor<EndpointUrls> constructor = EndpointUrls.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        // ENTAO
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

}