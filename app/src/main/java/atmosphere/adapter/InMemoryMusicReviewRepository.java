package atmosphere.adapter;

import atmosphere.domain.MusicReview;
import atmosphere.domain.MusicReviewRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    public Long nextId() {
        return ++lastId;
    }
}
