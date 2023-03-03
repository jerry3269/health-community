package project.healthcommunity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.domain.Trainer;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    List<Trainer> findByTrainerName(String trainerName);
}
