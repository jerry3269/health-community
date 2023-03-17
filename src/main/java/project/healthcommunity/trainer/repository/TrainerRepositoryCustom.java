package project.healthcommunity.trainer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.healthcommunity.trainer.dto.TrainerResult;
import project.healthcommunity.trainer.dto.TrainerSearchCond;

import java.util.List;

public interface TrainerRepositoryCustom {

    List<TrainerResult> search(TrainerSearchCond condition);

    Page<TrainerResult> searchPage(TrainerSearchCond condition, Pageable pageable);

    Page<TrainerResult> searchPage_optimization(TrainerSearchCond condition, Pageable pageable);
}
