package atmosphere.domain;

public class MusicReview {
    private final Long id;
    private String title;
    private String description;
    private String musicLink;

    public MusicReview(
        Long id,
        String musicLink,
        String reviewTitle,
        String description
    ) {
        this.id = id;
        this.title = reviewTitle;
        this.description = description;
        this.musicLink = musicLink;
    }

    public Long getId() {
        return id;
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
