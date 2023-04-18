package project.healthcommunity.trainer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.healthcommunity.trainer.dto.TrainerResponse;
import project.healthcommunity.trainer.dto.TrainerSearchCond;

public interface TrainerRepositoryCustom {

    Page<TrainerResponse> search(TrainerSearchCond condition, Pageable pageable);
}
