package atmosphere.application;

import atmosphere.domain.MusicRecommendationBox;
import atmosphere.domain.MusicRecommendationBoxRepository;
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
        if(findMyMusicRecommendationBox(userId).isPresent()) {
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
     * 서비스를 초기화합니다.
     */
    public void initialize() {
        repository.deleteAll();
    }
}
