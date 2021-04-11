package atmosphere.web;

import atmosphere.application.MusicRecommendApplicationService;
import atmosphere.web.spring.dto.MusicRecommendationBoxCreateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Nested
public class MusicRecommendationTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MusicRecommendApplicationService service;

    @BeforeEach
    void clearService() {
        service.initialize();
    }

    @Nested
    @DisplayName("음악 추천 상자를 생성할 때")
    class Describe_createMusicRecommendationBox {
        private final String userName = "LasWonho";

        @Nested
        @DisplayName("생성된 추천 상자가 없다면")
        class Context_notExistMusicRecommendationBox {
            @Test
            @DisplayName("음악 추천 상자가 생성된다.")
            void it_shouldCreateMusicRecommendationBox() throws Exception {
                MusicRecommendationBoxCreateDTO data = new MusicRecommendationBoxCreateDTO(userName);
                String requestBody = mapper.writeValueAsString(data);

                mockMvc.perform(
                    post("/music-recommendation-box")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(
                        content().string(containsString(userName)))
                    .andExpect(
                        status().is(201));
            }
        }

        @Nested
        @DisplayName("이미 추천 상자가 생성되었다면")
        class Context_alreadyExistMusicRecommendationBox {
            @BeforeEach
            void createReview() {
                service.createMusicRecommendationBox(userName);
            }

            @Test
            @DisplayName("요청이 실패한다.")
            void it_shouldFail() throws Exception {
                MusicRecommendationBoxCreateDTO data = new MusicRecommendationBoxCreateDTO(userName);
                String requestBody = mapper.writeValueAsString(data);

                mockMvc.perform(
                    post("/music-recommendation-box")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(
                        status().is(400));
            }
        }
    }
}
