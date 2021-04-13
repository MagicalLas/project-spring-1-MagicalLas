package atmosphere.domain;

import java.util.List;
import java.util.Optional;

public interface MusicReviewRepository {
    List<MusicReview> getAll();
    void add(MusicReview musicReview);
    void deleteAll();
    void delete(Long id);
    Long nextId();
    Optional<MusicReview> findById(Long id);
}
