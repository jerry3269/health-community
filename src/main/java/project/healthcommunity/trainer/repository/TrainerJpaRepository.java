package project.healthcommunity.trainer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.trainer.domain.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerJpaRepository extends JpaRepository<Trainer, Long>{

    List<Trainer> findByTrainerName(String trainerName);

    Optional<Trainer> findByLoginId(String loginId);
    Optional<Trainer> findById(Long id);
}
