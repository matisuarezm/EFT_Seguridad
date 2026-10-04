package com.duoc.EFTSeguridad;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class SecuredController {

    @RequestMapping("/greetings")
    public String greetings(@RequestParam(value="name", defaultValue="World") String name) {
        // Sanitizamos la entrada para prevenir XSS
        String cleanName = HtmlUtils.htmlEscape(name);
        return "Hello {" + cleanName + "}";
    }
}