package atmosphere.application;

import atmosphere.domain.MusicReview;
import atmosphere.domain.MusicReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 음악 리뷰에 대한 유즈케이스를 담당합니다.
 */
@Service
public class MusicReviewApplicationService {
    private final MusicReviewRepository repository;

    public MusicReviewApplicationService(MusicReviewRepository repository) {
        this.repository = repository;
    }
    /**
     * 음악 리뷰를 생성합니다.
     *
     * @param musicLink 음악의 링크
     * @param reviewTitle 리뷰에 붙힐 제목
     * @param description 리뷰 본문
     * @return 생성된 리뷰
     */
    public MusicReview createMusicReview(String musicLink, String reviewTitle, String description) {
        Long id = repository.nextId();
        MusicReview musicReview = new MusicReview(id, musicLink, reviewTitle, description);
        repository.add(musicReview);
        return musicReview;
    }

    /**
     * @return 생성된 모든 리뮤 목록
     */
    public List<MusicReview> findAllMusicReviews() {
        return repository.getAll();
    }

    /**
     * 서비스를 초기화합니다.
     */
    public void initialize() {
        repository.deleteAll();
    }

    /**
     * 특정한 리뷰를 찾습니다.
     *
     * @param id 찾으려는 리뷰의 아이디
     * @return 찾은 리뷰. 만약 리뷰를 찾지 못한다면 Optional.empty를 반환한다.
     */
    public Optional<MusicReview> findSpecificReview(Long id) {
        return repository.findById(id);
    }
}
