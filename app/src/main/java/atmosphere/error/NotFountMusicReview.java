package atmosphere.error;

public class NotFountMusicReview extends RuntimeException {
    public NotFountMusicReview(Long id) {
        super("Not Found Music Review. ID is " + id.toString());
    }
}
