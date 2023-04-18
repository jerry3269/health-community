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

import project.healthcommunity.trainer.repository.TrainerRepository;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class TrainerApiController {
    private final TrainerService trainerService;
    private final TrainerRepository trainerRepository;

    /**
     * @param request {
     *                "trainerName": "t11",
     *                "age": 11,
     *                "career": 11
     *                }
     * @return
     */
    @PostMapping("/api/trainer")
    public TrainerResponse saveTrainer(@RequestBody CreateTrainerRequest request) {
        Trainer trainer = new Trainer(request.getTrainerName(), request.getAge(), request.getCareer());
        trainerService.join(trainer);
        return new TrainerResponse(trainer);
    }

    @GetMapping("/api/trainers")
    public List<TrainerResponse> trainers() {
        return trainerService.trainers().stream().map(TrainerResponse::new).collect(toList());
    }

    @GetMapping("/api/trainer/{id}")
    public TrainerResponse trainerByParameter(@PathVariable Long id) {
        Trainer trainer = trainerService.findOne(id);
        return new TrainerResponse(trainer);
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
    public TrainerResponse updateTrainer(
            @PathVariable("id") Long id,
            @RequestBody UpdateTrainerRequest request) {

        Trainer findTrainer = trainerService.findOne(id);

        List<Certificate> certificates = request.getCertificateFormList()
                .stream()
                .map(c -> new Certificate(findTrainer, c.getCertificateName(), c.getAcquisitionDate()))
                .collect(toList());

        trainerService.update(id, request.getTrainerName(), certificates);

        return new TrainerResponse(findTrainer);
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
    @GetMapping("/api/trainer/search")
    public Page<TrainerResponse> searchTrainer(
            @RequestBody TrainerSearchCond condition,
            @PageableDefault(page = 0, size = 10, sort = "likes", direction = Sort.Direction.DESC) Pageable pageable) {
        return trainerRepository.search(condition, pageable);
    }


}
