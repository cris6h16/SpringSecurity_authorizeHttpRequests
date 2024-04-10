package org.cris6h16.springsecurity_authorizehttprequests.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class PrincipalController {

    // authorization happens twice
    @GetMapping("/auth") // 1. happens here
    public String auth() {
        return "auth"; // 2. happens here again for forwarding to thymeleaf to render the endpoint == FORWARD dispatch type
    }

    // authorization also happens twice
    @GetMapping("/hello") // 1. happens here
    public String hello() {
        throw new UnsupportedOperationException("unsupported"); // 2. happens here again for dispatching the error == ERROR dispatch type
        return "hello";
    }
}

