package atmosphere.web;

import atmosphere.application.MusicRecommendApplicationService;
import atmosphere.web.spring.dto.MusicDTO;
import atmosphere.web.spring.dto.MusicRecommendationBoxCreateDTO;
import atmosphere.web.spring.dto.MusicReviewDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Nested
    @DisplayName("나의 음악 추천함을 살펴볼 때")
    class Describe_findMyMusicRecommendationBox {
        private final String userName = "LasWonho";

        @Nested
        @DisplayName("생성된 추천 상자가 없다면")
        class Context_notExistMusicRecommendationBox {
            @Test
            void it_shouldFail() throws Exception {
                mockMvc.perform(get("/music-recommendation-box/" + userName))
                    .andExpect(
                        status().isNotFound()
                    );
            }
        }

        @Nested
        @DisplayName("추천 상자를 이미 생성한 경우")
        class Context_existMusicRecommendationBox {
            @BeforeEach
            void createBox() {
                service.createMusicRecommendationBox(userName);
            }
            @Test
            void it_shouldBeOk() throws Exception {
                mockMvc.perform(get("/music-recommendation-box/" + userName))
                    .andExpect(
                        status().isOk()
                    );
            }
        }
    }

    @Nested
    @DisplayName("다른 사람이 음악을 추천할 때")
    class Describe_recommendMusic {
        private final String userName = "LasWonho";
        private final String requestPath = "/music-recommendation-box/" + userName + "/recommend";
        private String requestBody;

        @BeforeEach
        void prepareValidRequestBody() throws JsonProcessingException {
            String musicLink = "https://www.youtube.com/watch?v=65BAeDpwzGY";
            String reviewTitle = "Sayuri - Mikazuki";
            String description = "사유리의 데뷔곡인 '미카즈키'이다. 란포기담 Game of Laplace의 엔딩으로 사용되었다.";
            MusicDTO data = new MusicDTO(musicLink, reviewTitle, description);

            requestBody = mapper.writeValueAsString(data);
        }

        @Nested
        @DisplayName("생성된 추천 상자가 없다면")
        class Context_notExistMusicRecommendationBox {
            @Test
            @DisplayName("에러가 발생한다")
            void it_shouldFail() throws Exception {
                mockMvc.perform(
                    post(requestPath)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(
                        status().isNotFound()
                    );
            }
        }

        @Nested
        @DisplayName("이미 추천 상자가 생성 된 경우")
        class Context_alreadyExistMusicRecommendationBox {
            @BeforeEach
            void createBox() {
                service.createMusicRecommendationBox(userName);
            }

            @Test
            @DisplayName("음악을 성공적으로 추천한다.")
            void it_shouldOk() throws Exception {
                mockMvc.perform(
                    post(requestPath)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(
                        status().isCreated()
                    );
            }
        }
    }
}
