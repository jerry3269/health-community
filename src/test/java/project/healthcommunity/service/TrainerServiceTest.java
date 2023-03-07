package project.healthcommunity.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class TrainerServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    TrainerService trainerService;

    @AfterEach
    void clear(){
        trainerService.clear();
    }

    @Test
    void join() {

        Trainer trainer1 = new Trainer("t1",10,1);
        trainerService.join(trainer1);

        Trainer findTrainer = trainerService.findOne(trainer1.getId());
        
        assertThat(findTrainer).isEqualTo(trainer1);

    }

    @Test
    void update() {
        Trainer trainer1 = new Trainer("t1",10,1);
        trainerService.join(trainer1);

        trainerService.update(trainer1.getId(), "t1", 20, 2);
        em.flush();
        em.clear();

        Trainer findTrainer = trainerService.findOne(trainer1.getId());

        System.out.println("findTrainer = " + findTrainer);
    }

    @Test
    void trainers() {
        Trainer trainer1 = new Trainer("t1",10,1);
        Trainer trainer2 = new Trainer("t2",20,2);
        Trainer trainer3 = new Trainer("t3",30,3);
        Trainer trainer4 = new Trainer("t4",40,4);
        Trainer trainer5 = new Trainer("t5",50,5);
        trainerService.join(trainer1);
        trainerService.join(trainer2);
        trainerService.join(trainer3);
        trainerService.join(trainer4);
        trainerService.join(trainer5);

        List<Trainer> trainers = trainerService.trainers();

        for (Trainer trainer : trainers) {
            System.out.println("trainer = " + trainer);
        }
    }

    @Test
    void DupTrainerTest(){
        Trainer trainer1 = new Trainer("t1",10,1);
        Trainer trainer2 = new Trainer("t1",20,2);
        trainerService.join(trainer1);

        assertThrows(IllegalStateException.class, () -> {
            trainerService.join(trainer2);
        });
    }



    @Test
    void deleteTrainer() {
        Trainer trainer1 = new Trainer("t1",10,1);
        Trainer trainer2 = new Trainer("t2",10,1);

        trainerService.join(trainer1);
        trainerService.join(trainer2);

        em.flush();
        em.clear();

        List<Trainer> trainers1 = trainerService.trainers();

        for (Trainer trainer : trainers1) {
            System.out.println("trainer = " + trainer);
        }

        trainerService.deleteTrainer(trainer1.getId());

        em.flush();
        em.clear();

        List<Trainer> trainers2 = trainerService.trainers();

        for (Trainer trainer : trainers2) {
            System.out.println("trainer = " + trainer);
        }
    }
}