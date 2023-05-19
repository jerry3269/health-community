package project.healthcommunity.global.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.trainer.domain.Trainer;

import static project.healthcommunity.global.error.ErrorStaticField.INVALID_ID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<Object> homeLoginArgumentResolver(@LoginForMember Member loginMember, @LoginForMember Trainer loginTrainer, Model model) {

        if (loginMember != null) {
            model.addAttribute("member", loginMember);
            return ResponseEntity.ok(loginMember);
        }

        if (loginTrainer != null) {
            model.addAttribute("trainer", loginTrainer);
            return ResponseEntity.ok(loginTrainer);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_ID);
    }
}
