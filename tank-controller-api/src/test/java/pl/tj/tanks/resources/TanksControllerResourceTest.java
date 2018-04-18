package pl.tj.tanks.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.tj.tanks.services.TankController;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.tj.tanks.controller.MovementDirection.FORWARD;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TanksControllerResource.class)
class TanksControllerResourceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TankController service;

    @Test
    void should_accept_movement() throws Exception {
        mvc.perform(put("/api/tank-controller/movement")
                .content(asJsonString(new MovementDetail(FORWARD)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void current_movement_will_return_current_movement_from_service() throws Exception {
        mvc.perform(get("/api/tank-controller/movement")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void current_movement_unsupported_media_types() throws Exception {
        mvc.perform(post("/api/tank-controller/movement")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void current_movement_server_error() throws Exception {
        when(service.currentTankMovement()).thenThrow(new RuntimeException("TEST"));

        mvc.perform(get("/api/tank-controller/movement")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", equalTo("TEST")))
                .andExpect(jsonPath("$.code", equalTo("TANK_1")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}