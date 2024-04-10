package org.cris6h16.springsecurity_authorizehttprequests.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {
    @GetMapping("/auth")
    public String auth() {
        return "auth";
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}

