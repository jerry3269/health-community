package project.healthcommunity.trainer.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.dto.CreateTrainerDto;
import project.healthcommunity.trainer.dto.TrainerDto;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService trainerService;

    @PostMapping("/api/save/trainer")
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
    public TrainerDto trainerByParameter(@PathVariable Long id){
        Trainer trainer = trainerService.findOne(id);
        return new TrainerDto(trainer);
    }

}
