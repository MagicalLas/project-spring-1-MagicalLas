package atmosphere.error;

public class AlreadyExistRecommendationBox extends RuntimeException{
    public AlreadyExistRecommendationBox() {
        super("Your RecommendationBox is already created.");
    }
}
