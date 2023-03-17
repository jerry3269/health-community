package project.healthcommunity.trainer.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.healthcommunity.trainer.dto.QTrainerDto is a Querydsl Projection type for TrainerDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QTrainerDto extends ConstructorExpression<TrainerDto> {

    private static final long serialVersionUID = 1046861128L;

    public QTrainerDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> trainerName, com.querydsl.core.types.Expression<Integer> age, com.querydsl.core.types.Expression<Integer> career) {
        super(TrainerDto.class, new Class<?>[]{long.class, String.class, int.class, int.class}, id, trainerName, age, career);
    }

}

