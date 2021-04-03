package atmosphere.application;

import atmosphere.domain.MusicReview;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 음악 리뷰에 대한 유즈케이스를 담당합니다.
 */
@Service
public class MusicReviewApplicationService {
    private final List<MusicReview> musicReviewList = new ArrayList<>();

    /**
     * 음악 리뷰를 생성합니다.
     *
     * @param musicLink 음악의 링크
     * @param reviewTitle 리뷰에 붙힐 제목
     * @param description 리뷰 본문
     * @return 생성된 리뷰
     */
    public MusicReview createMusicReview(String musicLink, String reviewTitle, String description) {
        MusicReview musicReview = new MusicReview(musicLink, reviewTitle, description);
        musicReviewList.add(musicReview);
        return musicReview;
    }

    /**
     * @return 생성된 모든 리뮤 목록
     */
    public List<MusicReview> findAllMusicReviews() {
        return musicReviewList;
    }

    /**
     * 서비스를 초기화합니다.
     */
    public void initialize() {
        musicReviewList.clear();
    }
}
