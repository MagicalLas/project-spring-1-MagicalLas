package atmosphere.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HealthCheckTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("서비스는 항상 available해야한다.")
    void checkServiceIsAvailable() throws Exception {
        mockMvc.perform(get("/actuator/health")).andExpect(
            status().is(200)
        );
    }
}
