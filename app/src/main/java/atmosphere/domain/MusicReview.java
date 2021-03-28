package atmosphere.domain;

public class MusicReview {
    private String title;
    private String description;
    private String musicLink;

    public MusicReview(String musicLink, String reviewTitle, String description) {
        this.title = reviewTitle;
        this.description = description;
        this.musicLink = musicLink;
    }
}
