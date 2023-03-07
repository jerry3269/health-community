package project.healthcommunity.trainer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.trainer.domain.Trainer;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    List<Trainer> findByTrainerName(String trainerName);
}
