package project.healthcommunity.trainer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrainerResult<T> {
    private T data;
    private int likes;
    private int certificateCount;
    private int postCount;
    private int commentCount;
}
