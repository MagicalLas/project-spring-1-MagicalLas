package atmosphere.domain;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MusicRecommendationBoxTest {
    @Test
    void createMusicRecommendationBox() {
        String userId = "Las";
        MusicRecommendationBox box = new MusicRecommendationBox(userId);
        assertThat(box).isNotNull();
    }

    @Test
    void recommendNewMusic() {
        String userId = "Las";
        MusicRecommendationBox box = new MusicRecommendationBox(userId);

        String musicLink = "https://www.youtube.com/watch?v=65BAeDpwzGY";
        String title = "Sayuri - Mikazuki";
        String description = "사유리의 데뷔곡인 '미카즈키'이다. 란포기담 Game of Laplace의 엔딩으로 사용되었다.";
        Recommendation music = new Recommendation(musicLink, title, description);

        box.recommend(music);

        assertThat(box.showAllRecommendation()).hasSize(1);
    }
}