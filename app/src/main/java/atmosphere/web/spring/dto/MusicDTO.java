package atmosphere.web.spring.dto;

import atmosphere.domain.Recommendation;

public class MusicDTO {
    public String title;
    public String description;
    public String musicLink;

    public MusicDTO(
        String musicLink,
        String title,
        String description
    ) {
        this.title = title;
        this.description = description;
        this.musicLink = musicLink;
    }
    public static MusicDTO fromModel(Recommendation model) {
        return new MusicDTO(model.getMusicLink(), model.getTitle(), model.getDescription());
    }
}
