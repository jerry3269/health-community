package project.healthcommunity.trainer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.dto.TrainerResponse;
import project.healthcommunity.trainer.dto.TrainerSearchCond;

import java.util.List;
import java.util.Optional;

public interface TrainerRepositoryCustom {

    Page<TrainerResponse> search(TrainerSearchCond condition, Pageable pageable);
    Trainer save(Trainer trainer);
    Trainer getByLoginId(String loginId);
    void deleteById(Long trainerId);
    void deleteAll();
    Trainer getById(Long trainerId);
    List<Trainer> findAll();
    List<Trainer> getByTrainerName(String trainerName);
    Optional<List<Trainer>> findByTrainerName(String trainerName);
    Optional<Trainer> findByLoginId(String loginId);
    Optional<Trainer> findById(Long trainerId);

}
