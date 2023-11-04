package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.cfg.JwtService;
import org.pl.deenes.infrastructure.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = MainController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class MainControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private TokenRepository tokenRepository;

    @Test
    void shouldRenderMainView() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
