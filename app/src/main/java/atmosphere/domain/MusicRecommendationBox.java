package atmosphere.domain;

public class MusicRecommendationBox {
    private final String id;

    public MusicRecommendationBox(String userId) {
        this.id = userId;
    }

    public String id() {
        return id;
    }
}
