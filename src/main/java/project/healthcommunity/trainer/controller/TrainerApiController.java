package project.healthcommunity.trainer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.dto.*;
import project.healthcommunity.trainer.repository.TrainerRepositoryCustom;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class TrainerApiController {
    private final TrainerService trainerService;
    private final TrainerRepositoryCustom trainerRepository;

    /**
     * @param request {
     *                "trainerName": "t11",
     *                "age": 11,
     *                "career": 11
     *                }
     * @return
     */
    @PostMapping("/api/trainer")
    public TrainerDto saveTrainer(@RequestBody CreateTrainerDto request) {
        Trainer trainer = new Trainer(request.getTrainerName(), request.getAge(), request.getCareer());
        trainerService.join(trainer);
        return new TrainerDto(trainer);
    }

    @GetMapping("/api/trainers")
    public List<TrainerDto> trainers() {
        return trainerService.trainers().stream().map(TrainerDto::new).collect(toList());
    }

    @GetMapping("/api/trainer/{id}")
    public TrainerDto trainerByParameter(@PathVariable Long id) {
        Trainer trainer = trainerService.findOne(id);
        return new TrainerDto(trainer);
    }

    /**
     * @param id
     * @param request {
     *                "trainerName": "t0",
     *                "certificateDtoList": [
     *                {
     *                "certificateName": "팔굽1급",
     *                "acquisitionDate": "2019-12-12"
     *                }
     *                ]
     *                }
     * @return
     */
    @PutMapping("/api/trainer/{id}")
    public TrainerDto updateTrainer(
            @PathVariable("id") Long id,
            @RequestBody UpdateTrainerDto request) {

        Trainer findTrainer = trainerService.findOne(id);

        List<Certificate> certificates = request.getCertificateDtoList()
                .stream()
                .map(c -> new Certificate(findTrainer, c.getCertificateName(), c.getAcquisitionDate()))
                .collect(toList());

        trainerService.update(id, request.getTrainerName(), certificates);

        return new TrainerDto(findTrainer);
    }

    /**
     * @param condition {
     *                  "trinaerName": "",
     *                  "ageGoe": ,
     *                  "careerGoe": ,
     *                  "certificateCountGoe": ,
     *                  "likesGoe": ,
     *                  "postCountGoe": ,
     *                  "commentCountGoe":
     *                  }
     * @return
     */
    @GetMapping("/api/trainer/search")
    public List<TrainerResult> searchTrainerV1(@RequestBody TrainerSearchCond condition) {
        return trainerRepository.search(condition);
    }

    /**
     * @param condition {
     *                  "trinaerName": "",
     *                  "ageGoe": ,
     *                  "careerGoe": ,
     *                  "certificateCountGoe": ,
     *                  "likesGoe": ,
     *                  "postCountGoe": ,
     *                  "commentCountGoe":
     *                  }
     * @param pageable
     * @return
     */
    @GetMapping("/api/trainer/search/page")
    public Page<TrainerResult> searchTrainerV2_page(
            @RequestBody TrainerSearchCond condition,
            @PageableDefault(page = 0, size = 10, sort = "likes", direction = Sort.Direction.DESC) Pageable pageable) {
        return trainerRepository.searchPage(condition, pageable);
    }

    @GetMapping("/api/trainer/search/page/optimize")
    public Page<TrainerResult> searchTrainerV3_page_optimization(
            @RequestBody TrainerSearchCond condition,
            @PageableDefault(page = 0, size = 10, sort = "likes", direction = Sort.Direction.DESC) Pageable pageable) {
        return trainerRepository.searchPage_optimization(condition, pageable);
    }
}
