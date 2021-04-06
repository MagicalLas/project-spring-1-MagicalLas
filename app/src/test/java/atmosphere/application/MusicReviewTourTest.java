package atmosphere.application;

import atmosphere.adapter.InMemoryMusicReviewRepository;
import atmosphere.domain.MusicReview;
import atmosphere.domain.MusicReviewRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Optional;

public class MusicReviewTourTest {
    private final MusicReviewRepository repository = new InMemoryMusicReviewRepository();
    private final MusicReviewApplicationService musicReviewService = new MusicReviewApplicationService(repository);
    private MusicReview musicReview;

    private List<MusicReview> allMusicReviews;
    private Optional<MusicReview> specificReview;

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

    @When("생성된 특정 리뷰를 가져온다면")
    public void getSpecificReview() {
        specificReview = musicReviewService.findSpecificReview(musicReview.getId());
    }

    @Then("생성된 리뷰가 리턴된다")
    public void returnCreatedReview() {
        Assertions.assertThat(specificReview).isNotEmpty();
        Assertions.assertThat(specificReview.get()).isEqualTo(musicReview);
    }

    @Given("음악 리뷰가 하나도 생성되지 않았을 때")
    public void emptyMusicReview() {
        // not exist music
        musicReview = new MusicReview(-1L, "", "", "");
    }

    @Then("리뷰를 찾지 못한다")
    public void cantFindReview() {
        Assertions.assertThat(specificReview).isEmpty();
    }
}
