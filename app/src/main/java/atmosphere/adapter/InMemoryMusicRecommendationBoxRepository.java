package atmosphere.adapter;

import atmosphere.domain.MusicRecommendationBox;
import atmosphere.domain.MusicRecommendationBoxRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryMusicRecommendationBoxRepository implements MusicRecommendationBoxRepository {
    private final Map<String, MusicRecommendationBox> cache;

    public InMemoryMusicRecommendationBoxRepository() {
        this.cache = new HashMap<>();
    }

    @Override
    public Optional<MusicRecommendationBox> findById(String id) {
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    public void save(MusicRecommendationBox model) {
        cache.put(model.id(), model);
    }

    @Override
    public void deleteAll() {
        cache.clear();
    }
}
