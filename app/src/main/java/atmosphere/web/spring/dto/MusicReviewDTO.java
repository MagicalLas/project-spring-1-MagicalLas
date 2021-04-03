package atmosphere.web.spring.dto;

import atmosphere.domain.MusicReview;

public class MusicReviewDTO {
    public MusicReviewDTO(String title, String description, String musicLink) {
        this.title = title;
        this.description = description;
        this.musicLink = musicLink;
    }
    static public MusicReviewDTO fromModel(MusicReview model) {
        return new MusicReviewDTO(
            model.getTitle(),
            model.getDescription(),
            model.getMusicLink()
        );
    }

    public String title;
    public String description;
    public String musicLink;
}
