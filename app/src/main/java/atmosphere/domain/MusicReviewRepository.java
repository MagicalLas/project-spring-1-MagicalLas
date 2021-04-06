package atmosphere.domain;

import java.util.List;

public interface MusicReviewRepository {
    List<MusicReview> getAll();
    void add(MusicReview musicReview);
    void deleteAll();
    Long nextId();
}
