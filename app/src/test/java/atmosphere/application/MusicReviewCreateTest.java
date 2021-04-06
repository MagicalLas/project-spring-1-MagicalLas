package atmosphere.application;

import atmosphere.adapter.InMemoryMusicReviewRepository;
import atmosphere.domain.MusicReview;
import atmosphere.domain.MusicReviewRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.util.List;

public class MusicReviewCreateTest {
    private final MusicReviewRepository repository = new InMemoryMusicReviewRepository();
    private final MusicReviewApplicationService musicReviewService = new MusicReviewApplicationService(repository);

    private String musicLink;
    private String reviewTitle;
    private String description;

    private MusicReview musicReview;


    @Given("노래 링크와 리뷰 제목, 노래 설명이 주어졌을 때")
    public void givenMusicInfo() {
        musicLink = "https://www.youtube.com/watch?v=65BAeDpwzGY";
        reviewTitle = "Sayuri - Mikazuki";
        description = "사유리의 데뷔곡인 '미카즈키'이다. 란포기담 Game of Laplace의 엔딩으로 사용되었다.";
    }

    @When("노래 리뷰를 생성한다면")
    public void createMusicReview() {
        musicReview = musicReviewService.createMusicReview(musicLink, reviewTitle, description);
    }

    @Then("노래 리뷰를 찾을 수 있습니다")
    public void shouldFindMusicReview() {
        List<MusicReview> allMusicReviews = musicReviewService.findAllMusicReviews();
        Assertions.assertThat(allMusicReviews).contains(musicReview);
    }
}
