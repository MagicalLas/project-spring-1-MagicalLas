package atmosphere.application;

import atmosphere.domain.*;
import atmosphere.error.AlreadyExistRecommendationBox;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * 음악 추천에 관련된 유즈케이스를 담당합니다.
 */
@Service
public class MusicRecommendApplicationService {
    private final MusicRecommendationBoxRepository repository;

    public MusicRecommendApplicationService(MusicRecommendationBoxRepository repository) {
        this.repository = repository;
    }

    public MusicRecommendationBox createMusicRecommendationBox(String userId) {
        if (findMyMusicRecommendationBox(userId).isPresent()) {
            throw new AlreadyExistRecommendationBox();
        }
        MusicRecommendationBox musicBox = new MusicRecommendationBox(userId);
        repository.save(musicBox);
        return musicBox;
    }

    /**
     * 자신의 음악 추천함을 찾습니다.
     *
     * @param userId 자신의 유저 아이디
     * @return 자신의 음악 추천함
     */
    public Optional<MusicRecommendationBox> findMyMusicRecommendationBox(String userId) {
        return repository.findById(userId);
    }

    /**
     * 음악 추천 상자에 새로운 음악을 추가합니다.
     *
     * @param id 음악이 추가된 상자 아이디
     * @param musicLink 음악 링크
     * @param title 음악 이름
     * @param description 추천 이유
     * @return 추천이 추가된 음악 추천 상자
     */
    public Optional<MusicRecommendationBox> recommendMusic(String id, String musicLink, String title, String description) {
        Optional<MusicRecommendationBox> box = repository.findById(id);
        Recommendation recommendation = new Recommendation(musicLink, title, description);
        box.map(
            (musicRecommendationBox) -> {
                musicRecommendationBox.recommend(recommendation);
                return musicRecommendationBox;
            }
        );
        return box;
    }

    /**
     * 서비스를 초기화합니다.
     */
    public void initialize() {
        repository.deleteAll();
    }
}
