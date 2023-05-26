package project.healthcommunity.trainer.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.exception.MemberDuplicationException;
import project.healthcommunity.member.repository.MemberRepositoryCustom;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.domain.TrainerSession;
import project.healthcommunity.trainer.dto.CreateTrainerRequest;
import project.healthcommunity.trainer.dto.TrainerResponse;
import project.healthcommunity.trainer.dto.UpdateTrainerRequest;
import project.healthcommunity.trainer.exception.TrainerPasswordNotMatchException;
import project.healthcommunity.trainer.repository.TrainerRepositoryCustom;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static project.healthcommunity.global.basic.BasicStaticField.LOGIN_TRAINER;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepositoryCustom trainerRepositoryCustom;
    private final MemberRepositoryCustom memberRepositoryCustom;

    @Transactional
    public TrainerResponse join(CreateTrainerRequest createTrainerRequest) {
        validDupLoginId(createTrainerRequest.getLoginId());
        Trainer trainer = CreateTrainerRequest.toTrainer(createTrainerRequest);
        trainerRepositoryCustom.save(trainer);
        return TrainerResponse.createByTrainer(trainer);
    }

    private void validDupLoginId(String loginId) {
        Optional<Member> findMember = memberRepositoryCustom.findByLoginId(loginId);
        if (findMember.isPresent()) {
            throw new MemberDuplicationException();
        }

        Optional<Trainer> findTrainer = trainerRepositoryCustom.findByLoginId(loginId);
        if (findTrainer.isPresent()) {
            throw new MemberDuplicationException();
        }
    }

    @Transactional
    public TrainerResponse login(LoginForm loginForm, HttpServletRequest request) {
        Trainer trainer = trainerRepositoryCustom.getByLoginId(loginForm.getLoginId());
        if (trainer.getPassword().equals(loginForm.getPassword())) {
            TrainerSession trainerSession = TrainerSession.createByTrainer(trainer);
            HttpSession session = request.getSession();
            session.setAttribute(LOGIN_TRAINER, trainerSession);
            return TrainerResponse.createByTrainerSession(trainerSession);
        }
        throw new TrainerPasswordNotMatchException();
    }

    public HttpSession logout(TrainerSession trainerSession, HttpServletRequest request) {
        trainerSession.invalidate(request);
        return request.getSession(false);
    }


    @Transactional
    public TrainerResponse update(TrainerSession trainerSession, UpdateTrainerRequest updateTrainerRequest) {
        Trainer findTrainer = findOne(trainerSession.getId());
        findTrainer.update(updateTrainerRequest);
        return TrainerResponse.createByTrainer(findTrainer);
    }

    @Transactional
    public void delete(TrainerSession trainerSession) {
        trainerRepositoryCustom.deleteById(trainerSession.getId());
    }

    @Transactional
    public void clear() {
        trainerRepositoryCustom.deleteAll();
    }


    public Trainer findOne(Long trainerId) {
        Trainer findTrainer = trainerRepositoryCustom.getById(trainerId);
        return findTrainer;
    }

    public List<TrainerResponse> trainers() {
        return trainerRepositoryCustom.findAll().stream().map(t -> TrainerResponse.createByTrainer(t)).collect(Collectors.toList());

    }


    public List<Trainer> findByTrainerName(String trainerName) {
        return trainerRepositoryCustom.findByTrainerName(trainerName);
    }

    public List<Comment> findCommentByTrainer(Long trainerId) {
        Trainer trainer = findOne(trainerId);
        return trainer.getCommentList();
    }

}
