package org.cris6h16.springsecurity_authorizehttprequests.Controllers;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PrincipalControllerTest {

    @Autowired
    MockMvc mvc;

    @Nested
    class authEndpoint {
        //.............................................................................
        @WithMockUser(authorities = "USER")
        @Test
        void auth_WhenUserAuthorityThenAuthorized() throws Exception {
            mvc.perform(get("/auth"))
                    .andExpect(status().isOk());
        }

        @WithMockUser(authorities = "USER", username = "jon")
        @Test
        void auth_WhenUserAuthorityAndNameThenAuthorized() throws Exception {
            mvc.perform(get("/auth/jon"))// should exist a user with name jon
                    .andExpect(status().isOk());
        }

        @WithMockUser(authorities = "USER", username = "cristian")
        @Test
        void auth_WhenUserAuthorityAndNameThenForbidden() throws Exception {
            mvc.perform(get("/auth/jon"))
                    .andExpect(status().isForbidden());
        }

        //.............................................................................
        @WithMockUser
        @Test
        void auth_WhenNotUserAuthorityThenForbidden() throws Exception {
            mvc.perform(get("/auth"))
                    .andExpect(status().isForbidden());
        }

        @WithMockUser
        @Test
        void auth_WhenNotUserAuthorityPathThenForbidden() throws Exception {
            mvc.perform(get("/auth/jon"))
                    .andExpect(status().isForbidden());
        }

        //.............................................................................

        @Test
        void hello_WhenUnauthenticatedThenUnauthorized() throws Exception {
            mvc.perform(get("/hello"))
                    .andExpect(status().isForbidden());
        }
    }


}