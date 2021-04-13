package atmosphere.domain;

import java.util.ArrayList;
import java.util.List;

public class MusicRecommendationBox {
    private final String id;
    private final List<Recommendation> recommendations;

    public MusicRecommendationBox(String userId) {
        this.id = userId;
        recommendations = new ArrayList<>();
    }

    public String id() {
        return id;
    }

    public void recommend(Recommendation music) {
        recommendations.add(music);
    }

    public List<Recommendation> showAllRecommendation() {
        return recommendations;
    }
}
