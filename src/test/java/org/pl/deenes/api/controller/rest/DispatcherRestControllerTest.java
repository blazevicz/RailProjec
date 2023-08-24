package org.pl.deenes.api.controller.rest;

import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.pl.deenes.api.controller.dto.DispatcherDTO;
import org.pl.deenes.api.controller.mapper.DispatcherDTOMapper;
import org.pl.deenes.infrastructure.repositories.DispatcherRepository;
import org.pl.deenes.model.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DispatcherRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class DispatcherRestControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private DispatcherDTOMapper dispatcherDTOMapper;
    @MockBean
    private DispatcherRepository dispatcherRepository;

    @Test
    void allDispatchers() throws Exception {
        List<Dispatcher> dispatchers = List.of(Dispatcher.builder()
                .name("A")
                .surname("B")
                .phoneNumber("32 1231231").build());

        DispatcherDTO dispatcherDTO = DispatcherDTO.builder()
                .name("A")
                .surname("B")
                .phoneNumber("32 1231231").build();

        when(dispatcherRepository.findAll()).thenReturn(dispatchers);

        when(dispatcherDTOMapper.mapToDTO(any())).thenReturn(dispatcherDTO);

        mockMvc.perform(get("/api/dispatcher/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(dispatcherDTO.getName())))
                .andExpect(jsonPath("$[0].surname", Matchers.is(dispatcherDTO.getSurname())))
                .andExpect(jsonPath("$[0].phoneNumber", Matchers.is(dispatcherDTO.getPhoneNumber())));
    }

    @Test
    void addDispatcher() throws Exception {
        DispatcherDTO dispatcherDTO = DispatcherDTO.builder()
                .name("Witold")
                .surname("Jackowski")
                .phoneNumber("32 222 11 11")
                .build();

        Dispatcher dispatcher = Dispatcher.builder()
                .id(1)
                .name("Witold")
                .surname("Jackowski")
                .phoneNumber("32 222 11 11")
                .build();

        when(dispatcherDTOMapper.mapFromDTO(dispatcherDTO)).thenReturn(dispatcher);
        when(dispatcherRepository.save(any())).thenReturn(dispatcher);
        when(dispatcherDTOMapper.mapToDTO(dispatcher)).thenReturn(dispatcherDTO);

        mockMvc.perform(post("/api/dispatcher")
                        .contentType("application/json")
                        .content("{\"name\":\"Witold\",\"surname\":\"Jackowski\",\"phoneNumber\":\"32 222 11 11\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Witold"))
                .andExpect(jsonPath("$.surname").value("Jackowski"))
                .andExpect(jsonPath("$.phoneNumber").value("32 222 11 11"));
    }
}
