package atmosphere.web.spring.dto;

import atmosphere.domain.MusicReview;

public class MusicReviewDTO {
    public Long id;
    public String title;
    public String description;
    public String musicLink;

    public MusicReviewDTO(Long id, String title, String description, String musicLink) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.musicLink = musicLink;
    }
    static public MusicReviewDTO fromModel(MusicReview model) {
        return new MusicReviewDTO(
            model.getId(),
            model.getTitle(),
            model.getDescription(),
            model.getMusicLink()
        );
    }
}
