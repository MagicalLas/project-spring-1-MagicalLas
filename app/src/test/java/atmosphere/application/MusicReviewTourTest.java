package atmosphere.application;

import atmosphere.domain.MusicReview;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.util.List;

public class MusicReviewTourTest {
    private final MusicReviewApplicationService musicReviewService = new MusicReviewApplicationService();
    private MusicReview musicReview;
    private List<MusicReview> allMusicReviews;

    @Given("음악 리뷰가 이미 생성되었을 때")
    public void alreadyCreateMusicReview() {
        String musicLink= "https://www.youtube.com/watch?v=65BAeDpwzGY";
        String reviewTitle= "Sayuri - Mikazuki";
        String description= "사유리의 데뷔곡인 '미카즈키'이다. 란포기담 Game of Laplace의 엔딩으로 사용되었다.";

        musicReview = musicReviewService.createMusicReview(musicLink, reviewTitle, description);
    }

    @When("모든 리뷰 리스트를 가져온다면")
    public void getAllReviews() {
        allMusicReviews = musicReviewService.findAllMusicReviews();
    }

    @Then("이미 생성된 모든 리뷰가 포함된다")
    public void shouldContainAllCreatedReviews() {
        Assertions.assertThat(allMusicReviews).contains(musicReview);
        Assertions.assertThat(allMusicReviews).hasSize(1);
    }
}
