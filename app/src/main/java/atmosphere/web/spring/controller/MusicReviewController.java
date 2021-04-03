package atmosphere.web.spring.controller;

import atmosphere.application.MusicReviewApplicationService;
import atmosphere.web.spring.dto.MusicReviewDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/music-reviews")
public class MusicReviewController {
    private final MusicReviewApplicationService service;

    public MusicReviewController(MusicReviewApplicationService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    List<MusicReviewDTO> getAllReviews(){
        return service.findAllMusicReviews()
            .stream()
            .map(MusicReviewDTO::fromModel)
            .collect(Collectors.toList());
    }
}
