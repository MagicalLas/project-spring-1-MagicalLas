package atmosphere.web.spring.controller;

import atmosphere.application.MusicReviewApplicationService;
import atmosphere.domain.MusicReview;
import atmosphere.web.spring.dto.MusicReviewDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "https://atmop.dev")
@RequestMapping(path = "/music-reviews", produces = "application/json;charset=UTF-8")
public class MusicReviewController {
    private final MusicReviewApplicationService service;

    public MusicReviewController(MusicReviewApplicationService service) {
        this.service = service;
    }

    @GetMapping
    List<MusicReviewDTO> getAllReviews(){
        return service.findAllMusicReviews()
            .stream()
            .map(MusicReviewDTO::fromModel)
            .collect(Collectors.toList());
    }

    @PostMapping
    MusicReviewDTO createReview(@RequestBody MusicReviewDTO data){
        MusicReview createdReview = service.createMusicReview(data.musicLink, data.title, data.description);
        return MusicReviewDTO.fromModel(createdReview);
    }
}
