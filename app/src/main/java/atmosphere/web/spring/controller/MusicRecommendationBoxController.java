package atmosphere.web.spring.controller;

import atmosphere.application.MusicRecommendApplicationService;
import atmosphere.domain.MusicRecommendationBox;
import atmosphere.error.NotFountMusicRecommendationBox;
import atmosphere.web.spring.dto.MusicRecommendationBoxCreateDTO;
import atmosphere.web.spring.dto.MusicRecommendationBoxDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://atmop.dev")
@RequestMapping(path = "/music-recommendation-box", produces = "application/json;charset=UTF-8")
public class MusicRecommendationBoxController {
    private final MusicRecommendApplicationService service;

    public MusicRecommendationBoxController(MusicRecommendApplicationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicRecommendationBoxDTO createMusicRecommendationBox(
        @RequestBody MusicRecommendationBoxCreateDTO createDTO
    ) {
        MusicRecommendationBox musicBox = service.createMusicRecommendationBox(createDTO.userId);
        return MusicRecommendationBoxDTO.fromModel(musicBox);
    }

    @GetMapping("{id}")
    public MusicRecommendationBoxDTO getMyRecommendationBox(
        @PathVariable String id
    ) {
        Optional<MusicRecommendationBox> musicBox = service.findMyMusicRecommendationBox(id);
        return musicBox.map(
            MusicRecommendationBoxDTO::fromModel
        ).orElseThrow(
            () -> new NotFountMusicRecommendationBox(id)
        );
    }
}
