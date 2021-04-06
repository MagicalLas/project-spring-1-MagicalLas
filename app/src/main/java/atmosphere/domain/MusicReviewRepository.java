package atmosphere.domain;

import java.util.List;
import java.util.Optional;

public interface MusicReviewRepository {
    List<MusicReview> getAll();
    void add(MusicReview musicReview);
    void deleteAll();
    Long nextId();
    Optional<MusicReview> findById(Long id);
}
