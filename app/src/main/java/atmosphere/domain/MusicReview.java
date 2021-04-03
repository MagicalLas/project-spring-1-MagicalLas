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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMusicLink() {
        return musicLink;
    }
}
