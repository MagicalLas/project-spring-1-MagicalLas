package atmosphere.domain;

import java.util.Optional;

public interface MusicRecommendationBoxRepository {
    Optional<MusicRecommendationBox> findById(String id);
    void save(MusicRecommendationBox model);
}
