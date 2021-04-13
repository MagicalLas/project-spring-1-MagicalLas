package atmosphere.domain;

public class Recommendation {
    private String title;
    private String description;
    private String musicLink;

    public Recommendation(
        String musicLink,
        String title,
        String description
    ) {
        this.title = title;
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
