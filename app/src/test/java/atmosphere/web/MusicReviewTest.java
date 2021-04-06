package atmosphere.web;

import atmosphere.application.MusicReviewApplicationService;
import atmosphere.domain.MusicReview;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Nested
public class MusicReviewTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MusicReviewApplicationService service;

    @BeforeEach
    void clearService() {
        service.initialize();
    }

    @Nested
    @DisplayName("모든 음악 리뷰를 가져올 때")
    class Describe_getAllReviews {
        @Nested
        @DisplayName("생성된 리뷰가 없다면")
        class Context_notExistReviews {
            @Test
            @DisplayName("비어있는 리스트를 반환한다.")
            void it_shouldReturnEmptyList() throws Exception {
                mockMvc.perform(get("/music-reviews"))
                    .andExpect(
                        status().is(200))
                    .andExpect(
                        content().string("[]"));
            }
        }

        @Nested
        @DisplayName("리뷰가 이미 생성되어 있다면")
        class Context_alreadyExistReview {
            private String musicLink;
            private String reviewTitle;
            private String description;

            @BeforeEach
            void createReview() {
                musicLink = "https://www.youtube.com/watch?v=65BAeDpwzGY";
                reviewTitle = "Sayuri - Mikazuki";
                description = "사유리의 데뷔곡인 '미카즈키'이다. 란포기담 Game of Laplace의 엔딩으로 사용되었다.";
                service.createMusicReview(musicLink, reviewTitle, description);
            }

            @Test
            @DisplayName("생성된 리뷰를 반환한다.")
            void it_shouldReturnAllMusicList() throws Exception {
                mockMvc.perform(get("/music-reviews").accept(MediaType.APPLICATION_JSON))
                    .andExpect(
                        status().is(200))
                    .andExpect(
                        content().string(containsString(reviewTitle)))
                    .andExpect(
                        content().string(containsString(musicLink)))
                    .andExpect(
                        content().string(containsString(description)));
            }
        }
    }

    @Nested
    @DisplayName("특정 리뷰를 가져올 때")
    class Describe_getSpecificReview {
        @Nested
        @DisplayName("생성된 리뷰가 없다면")
        class Context_notExistReviews {
            @Test
            @DisplayName("404를 반환한다.")
            void it_shouldReturn404() throws Exception {
                mockMvc.perform(get("/music-reviews/9999"))
                    .andExpect(
                        status().is(404));
            }
        }
        @Nested
        @DisplayName("리뷰가 이미 생성되어 있다면")
        class Context_alreadyExistReview {
            private String musicLink;
            private String reviewTitle;
            private String description;
            private MusicReview createdMusicReview;

            @BeforeEach
            void createReview() {
                musicLink = "https://www.youtube.com/watch?v=65BAeDpwzGY";
                reviewTitle = "Sayuri - Mikazuki";
                description = "사유리의 데뷔곡인 '미카즈키'이다. 란포기담 Game of Laplace의 엔딩으로 사용되었다.";
                createdMusicReview = service.createMusicReview(musicLink, reviewTitle, description);
            }

            @Test
            @DisplayName("생성된 리뷰를 반환한다.")
            void it_shouldReturnSpecificReview() throws Exception {
                String id = createdMusicReview.getId().toString();
                mockMvc.perform(get("/music-reviews/" + id).accept(MediaType.APPLICATION_JSON))
                    .andExpect(
                        status().is(200))
                    .andExpect(
                        content().string(containsString(reviewTitle)))
                    .andExpect(
                        content().string(containsString(musicLink)))
                    .andExpect(
                        content().string(containsString(description)));
            }
        }
    }

    @Nested
    @DisplayName("음악 리뷰를 작성할 때")
    class Describe_createReview {
        @Nested
        @DisplayName("입력 값이 유효하다면")
        class Context_validRequestData {
            private String requestBody;

            @BeforeEach
            void prepareValidRequestBody() throws JsonProcessingException {
                String musicLink = "https://www.youtube.com/watch?v=65BAeDpwzGY";
                String reviewTitle = "Sayuri - Mikazuki";
                String description = "사유리의 데뷔곡인 '미카즈키'이다. 란포기담 Game of Laplace의 엔딩으로 사용되었다.";
                MusicReviewDTO data = new MusicReviewDTO(1L, musicLink, reviewTitle, description);

                requestBody = mapper.writeValueAsString(data);
            }
            @Test
            @DisplayName("리뷰가 생성된다.")
            void it_shouldBeOk() throws Exception {
                mockMvc.perform(
                    post("/music-reviews")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            }
        }

        @Nested
        @DisplayName("입력 값이 유효하지 않다면")
        class Context_invalidRequestData {
            private String requestBody;

            @BeforeEach
            void prepareInvalidRequestBody() {
                requestBody = "";
            }
            @Test
            @DisplayName("리뷰가 생성되지 않는다.")
            void it_shouldBeBadRequest() throws Exception {
                mockMvc.perform(
                    post("/music-reviews")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
            }
        }
    }
}
