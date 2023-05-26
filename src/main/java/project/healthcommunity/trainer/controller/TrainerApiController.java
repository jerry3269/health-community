package project.healthcommunity.trainer.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.global.controller.LoginForTrainer;
import project.healthcommunity.global.dto.LoginForm;
import project.healthcommunity.global.exception.BindingException;
import project.healthcommunity.member.dto.MemberResponse;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.domain.TrainerSession;
import project.healthcommunity.trainer.dto.*;

import project.healthcommunity.trainer.repository.TrainerJpaRepository;
import project.healthcommunity.trainer.repository.TrainerRepositoryCustom;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.List;

import static java.util.stream.Collectors.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer")
public class TrainerApiController {
    private final TrainerService trainerService;
    private final TrainerRepositoryCustom TrainerRepositoryCustom;

    @PostMapping("/login")
    public ResponseEntity<TrainerResponse> login(@RequestBody @Valid LoginForm loginForm, HttpServletRequest request) {
        TrainerResponse trainerResponse = trainerService.login(loginForm, request);
        return ResponseEntity.ok(trainerResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@LoginForTrainer TrainerSession trainerSession, HttpServletRequest request) {
        HttpSession session = trainerService.logout(trainerSession, request);
        return ResponseEntity.ok(session);
    }

    @PostMapping("/add")
    public ResponseEntity<TrainerResponse> saveTrainer(@RequestBody @Valid CreateTrainerRequest createTrainerRequest) {
        TrainerResponse trainerResponse = trainerService.join(createTrainerRequest);
        return ResponseEntity.ok(trainerResponse);
    }

    @GetMapping("/")
    public ResponseEntity trainerByParameter(@LoginForTrainer TrainerSession trainerSession) {
        Trainer trainer = trainerService.getById(trainerSession.getId());
        TrainerResponse trainerResponse = TrainerResponse.createByTrainer(trainer);
        return ResponseEntity.ok(trainerResponse);
    }

    @PatchMapping("/")
    public ResponseEntity updateTrainer(
            @LoginForTrainer TrainerSession trainerSession,
            @RequestBody @Valid UpdateTrainerRequest updateTrainerRequest) {

        TrainerResponse trainerResponse = trainerService.update(trainerSession, updateTrainerRequest);
        return ResponseEntity.ok(trainerResponse);
    }

    @DeleteMapping("/")
    public ResponseEntity delete(@LoginForTrainer TrainerSession trainerSession) {
        trainerService.delete(trainerSession);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity search(
            @ModelAttribute @Valid TrainerSearchCond condition,
            BindingResult bindingResult,
            @PageableDefault(page = 0, size = 10, sort = "likes", direction = Sort.Direction.DESC) Pageable pageable) {

        BindingException.validate(bindingResult);
        Page<TrainerResponse> trainerResponsePage = TrainerRepositoryCustom.search(condition, pageable);
        return ResponseEntity.ok(trainerResponsePage);
    }

    @GetMapping("/trainers")
    public ResponseEntity<List<TrainerResponse>> trainers() {
        return ResponseEntity.ok(trainerService.findAll());
    }

}
