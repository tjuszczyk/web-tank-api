package pl.tj.tanks.resources;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.tj.tanks.services.TankController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TanksControllerResource.class)
class TanksControllerResourceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TankController service;

    @Test
    void should_accept_movement() {

    }

    @Test
    void current_movement_will_return_current_movement_from_service() throws Exception {
        mvc.perform(get("/api/tank-controller/movement")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}