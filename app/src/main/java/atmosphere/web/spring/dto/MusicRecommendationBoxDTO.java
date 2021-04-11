package atmosphere.web.spring.dto;

import atmosphere.domain.MusicRecommendationBox;

public class MusicRecommendationBoxDTO {
    public String id;

    public MusicRecommendationBoxDTO(String id) {
        this.id = id;
    }

    public static MusicRecommendationBoxDTO fromModel(MusicRecommendationBox model) {
        return new MusicRecommendationBoxDTO(model.id());
    }
}

