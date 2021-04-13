package atmosphere.error;

public class NotFountMusicRecommendationBox extends RuntimeException {
    public NotFountMusicRecommendationBox(String id) {
        super("Not Found Music Recommendation Box. ID is " + id);
    }
}
