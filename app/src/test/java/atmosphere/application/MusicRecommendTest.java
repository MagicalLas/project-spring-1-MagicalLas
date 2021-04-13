package atmosphere.application;

import atmosphere.adapter.InMemoryMusicRecommendationBoxRepository;
import atmosphere.domain.MusicRecommendationBox;
import atmosphere.domain.MusicRecommendationBoxRepository;
import atmosphere.error.AlreadyExistRecommendationBox;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MusicRecommendTest {
    private String userId;
    private final MusicRecommendationBoxRepository repository =
        new InMemoryMusicRecommendationBoxRepository();
    private final MusicRecommendApplicationService service =
        new MusicRecommendApplicationService(repository);
    private MusicRecommendationBox musicBox;
    private Optional<MusicRecommendationBox> usersMusicBox;

    @Given("어떤 유저가 존재할 때")
    public void someUserExist() {
        userId = "LasWonho";
    }

    @When("음악 추천함을 만들면")
    public void makeMusicRecommendationBox() {
        musicBox = service.createMusicRecommendationBox(userId);
    }

    @Then("그 유저만의 음악 추천함이 만들어진다")
    public void madeUsersMusicRecommendationBox() {
        Assertions.assertThat(musicBox).isNotNull();
        Optional<MusicRecommendationBox> myMusicBox = service.findMyMusicRecommendationBox(userId);
        Assertions.assertThat(myMusicBox).isNotEmpty();
    }

    @And("음악 추천함을 만들었으면")
    public void alreadyCreatedMusicRecommendationBox() {
        musicBox = service.createMusicRecommendationBox(userId);
    }

    @When("그 유저의 음악 추천함을 찾는다면")
    public void findUsersMusicRecommendationBox() {
        usersMusicBox = service.findMyMusicRecommendationBox(userId);
    }

    @Then("음악 추천함이 존재해야한다")
    public void shouldExistMusicBox() {
        Assertions.assertThat(usersMusicBox).isNotEmpty();
        Assertions.assertThat(usersMusicBox.get()).isEqualTo(musicBox);
    }

    @When("새로운 음악 추천함을 만든다면")
    public void makeNewMusicRecommendationBox() {
    }

    @Then("에러가 나며 음악 추천함을 만들 수 없다")
    public void raiseError() {
        assertThrows(
            AlreadyExistRecommendationBox.class,
            () -> service.createMusicRecommendationBox(userId)
        );
    }

    @When("그 추천함에 음악을 추천 한다면")
    public void recommendMusic() {
        String musicLink = "https://www.youtube.com/watch?v=65BAeDpwzGY";
        String title = "Sayuri - Mikazuki";
        String description = "사유리의 데뷔곡인 '미카즈키'이다. 란포기담 Game of Laplace의 엔딩으로 사용되었다.";

        usersMusicBox = service.recommendMusic(musicBox.id(), musicLink, title, description);
    }

    @Then("음악 추천함에 새로운 추천이 생긴다")
    public void shouldHaveNewRecommendations() {
        Assertions.assertThat(usersMusicBox).isNotEmpty();
        Assertions.assertThat(usersMusicBox.get().showAllRecommendation()).hasSize(1);
    }
}
