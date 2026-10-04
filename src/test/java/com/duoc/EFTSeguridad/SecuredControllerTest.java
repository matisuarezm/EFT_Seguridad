package com.duoc.EFTSeguridad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecuredControllerTest {

    private final SecuredController controller = new SecuredController();

    @Test
    void testGreetingsDefault() {
        String response = controller.greetings("World");
        assertEquals("Hello {World}", response);
    }

    @Test
    void testGreetingsConSanitizacionXSS() {
        String response = controller.greetings("<script>alert('xss')</script>");
        assertEquals("Hello {&lt;script&gt;alert(&#39;xss&#39;)&lt;/script&gt;}", response);
    }
}