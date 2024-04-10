package org.cris6h16.springsecurity_authorizehttprequests.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PermissionBasedControllerTest {

    @Autowired
    MockMvc mvc;

    @WithMockUser(authorities = "read")
    @Test
    void getWhenReadAuthorityThenAuthorized() throws Exception {
        this.mvc.perform(get("/resource/any")) // should exist or 404
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    void getWhenNoReadAuthorityThenForbidden() throws Exception {
        this.mvc.perform(get("/resource/any"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = "write")
    @Test
    void postWhenWriteAuthorityThenAuthorized() throws Exception {
        this.mvc.perform(post("/resource/any").with(csrf()))
                .andExpect(status().isOk()); // should exist or 404
    }

    @WithMockUser(authorities = "read")
    @Test
    void postWhenNoWriteAuthorityThenForbidden() throws Exception {
        this.mvc.perform(get("/any").with(csrf()))
                .andExpect(status().isForbidden());
    }
}
