package atmosphere.web;

import atmosphere.application.MusicReviewApplicationService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Nested
public class MusicReviewTest {
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
            void it_shouldReturnEmptyList() throws Exception {
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
}
