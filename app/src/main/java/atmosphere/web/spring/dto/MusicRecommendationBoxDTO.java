package atmosphere.web.spring.dto;

import atmosphere.domain.MusicRecommendationBox;
import atmosphere.domain.Recommendation;

import java.util.List;
import java.util.stream.Collectors;

public class MusicRecommendationBoxDTO {
    public String id;
    public List<MusicDTO> recommendationList;

    public MusicRecommendationBoxDTO(String id, List<MusicDTO> recommendationList) {
        this.id = id;
        this.recommendationList = recommendationList;
    }

    public static MusicRecommendationBoxDTO fromModel(MusicRecommendationBox model) {
        List<Recommendation> recommendations = model.showAllRecommendation();
        List<MusicDTO> recommendationList = recommendations.stream().map(
            MusicDTO::fromModel
        ).collect(Collectors.toList());
        return new MusicRecommendationBoxDTO(model.id(), recommendationList);
    }
}

