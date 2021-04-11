package atmosphere.web.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MusicRecommendationBoxCreateDTO {
    public String userId;

    public MusicRecommendationBoxCreateDTO(
        @JsonProperty("userId") String userId
    ) {
        this.userId = userId;
    }
}
