package project.healthcommunity.trainer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.certificate.dto.CertificateDto;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.certificate.repository.CertificateRepository;
import project.healthcommunity.trainer.repository.TrainerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final CertificateRepository certificateRepository;


    @Transactional(noRollbackForClassName = {"IllegalStateException"})
    public void join(Trainer trainer){
        validDupTrainer(trainer);
        trainerRepository.save(trainer);
    }


    private void validDupTrainer(Trainer trainer) {
        List<Trainer> findTrainer = trainerRepository.findByTrainerName(trainer.getTrainerName());
        if(!findTrainer.isEmpty()){
            throw new IllegalStateException("이미 존재하는 트레이너입니다.");
        }
    }


    @Transactional
    public void update(Long id, String trainerName, List<Certificate> certificates){
        Trainer findTrainer = findOne(id);
        findTrainer.update(trainerName, certificates);
    }


    public Trainer findOne(Long id){
        Optional<Trainer> findTrainer = trainerRepository.findById(id);
        if(!findTrainer.isPresent()){
            throw new IllegalStateException("존재하지 않는 트레이너입니다.");
        }
        return findTrainer.get();
    }

    /**
     * 전체 조회
     */
    public List<Trainer> trainers(){
        return trainerRepository.findAll();
    }


    @Transactional
    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }

    public void clear(){
        trainerRepository.deleteAll();
    }

    public List<Trainer> findByTrainerName(String trainerName) {
        return trainerRepository.findByTrainerName(trainerName);
    }

    public List<Comment> findCommentByTrainer(Long id) {
        Trainer trainer = findOne(id);
        return trainer.getCommentList();
    }
}
