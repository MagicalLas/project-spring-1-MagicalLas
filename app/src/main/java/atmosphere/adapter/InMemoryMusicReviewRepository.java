package atmosphere.adapter;

import atmosphere.domain.MusicReview;
import atmosphere.domain.MusicReviewRepository;
import atmosphere.error.NotFountMusicReview;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryMusicReviewRepository implements MusicReviewRepository {
    private final List<MusicReview> musicReviewList = new ArrayList<>();
    private Long lastId = 0L;

    @Override
    public List<MusicReview> getAll() {
        return musicReviewList;
    }

    @Override
    public void add(MusicReview musicReview) {
        musicReviewList.add(musicReview);
    }

    @Override
    public void deleteAll() {
        musicReviewList.clear();
    }

    @Override
    public void delete(Long id) {
        MusicReview targetReview = findById(id).orElseThrow(
            () -> new NotFountMusicReview(id)
        );
        musicReviewList.remove(targetReview);
    }

    @Override
    public Long nextId() {
        return ++lastId;
    }

    @Override
    public Optional<MusicReview> findById(Long id) {
        return musicReviewList
            .stream()
            .filter(
                musicReview -> musicReview.getId().equals(id)
            ).findAny();
    }
}
