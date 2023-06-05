package project.healthcommunity.global.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.web.WebAppConfiguration;
import project.healthcommunity.certificate.controller.CertificateController;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.certificate.dto.CreateCertificateRequest;
import project.healthcommunity.certificate.dto.UpdateCertificateRequest;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.util.ControllerTest;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CertificateControllerTest extends ControllerTest {

    private Trainer testTrainer;
    private Certificate testCertificate;

    private void initialList() {
        testTrainer = createAndSaveTestTrainer();
        testCertificate = new Certificate(testTrainer, "testCertificate", LocalDate.of(2000, 12, 18));
        certificateJpaRepository.save(testCertificate);
    }

    @Test
    @DisplayName("자격증 추가 성공 200")
    void add200() throws Exception {
        Trainer testTrainer = createAndSaveTestTrainer();
        MockHttpSession trainerSession = getTrainerSession(testTrainer);
        CreateCertificateRequest createCertificateRequest = CreateCertificateRequest.builder()
                .trainerId(testTrainer.getId())
                .certificateName("윗몸")
                .acquiredDate(LocalDate.of(2000, 12, 18))
                .build();

        String string = objectMapper.writeValueAsString(createCertificateRequest);

        mockMvc.perform(post("/certificate/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(trainerSession)
                        .content(string))
                .andExpect(jsonPath("$.trainerId", is(testTrainer.getId().intValue())))
                .andExpect(jsonPath("$.certificateName", is("윗몸")))
                .andExpect(jsonPath("$.acquiredDate", is(LocalDate.of(2000, 12, 18).toString())));
    }

    @Test
    @DisplayName("자격증 조회 성공 200")
    void list200() throws Exception {
        initialList();
        MockHttpSession trainerSession = getTrainerSession(testTrainer);

        mockMvc.perform(get("/certificate/list")
                        .session(trainerSession))
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].trainerId", is(testTrainer.getId().intValue())))
                .andExpect(jsonPath("$[0].certificateName", is("testCertificate")))
                .andExpect(jsonPath("$[0].acquiredDate", is("2000-12-18")));
    }

    @Test
    @DisplayName("자격증 업데이트 성공 200")
    void update200() throws Exception {
        initialList();
        MockHttpSession trainerSession = getTrainerSession(testTrainer);

        UpdateCertificateRequest updateCertificateRequest = UpdateCertificateRequest.builder()
                .certificateId(testCertificate.getId())
                .acquiredDate(LocalDate.now())
                .build();

        String string = objectMapper.writeValueAsString(updateCertificateRequest);

        mockMvc.perform(patch("/certificate/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(string)
                        .session(trainerSession))
                .andExpect(jsonPath("$.trainerId", is(testTrainer.getId().intValue())))
                .andExpect(jsonPath("$.certificateName", is("testCertificate")))
                .andExpect(jsonPath("$.acquiredDate", is(LocalDate.now().toString())));
    }

    @Test
    @DisplayName("자격증 삭제 성공 200")
    void delete200() throws Exception {
        initialList();
        MockHttpSession trainerSession = getTrainerSession(testTrainer);

        mockMvc.perform(delete("/certificate/delete/{certificateId}", testCertificate.getId())
                        .session(trainerSession))
                .andExpect(status().isOk());
    }
}
