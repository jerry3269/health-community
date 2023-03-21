package project.healthcommunity.trainer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.healthcommunity.trainer.dto.TrainerResult;
import project.healthcommunity.trainer.dto.TrainerSearchCond;

public interface TrainerRepositoryCustom {

    Page<TrainerResult> search(TrainerSearchCond condition, Pageable pageable);
}
