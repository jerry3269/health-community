package project.healthcommunity.global.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import project.healthcommunity.post.dto.CreatePostRequest;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.util.ControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends ControllerTest {

    @Test
    @DisplayName("Get Post list 200")
    void list200() throws Exception {
        mockMvc.perform(get("/post/list"))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("post 등록 성공 200")
    void save200() throws Exception {
        Trainer testTrainer = createAndSaveTestTrainer();
        MockHttpSession trainerSession = getTrainerSession(testTrainer);

        CreatePostRequest createTestPostRequest = createTestPostRequest(testTrainer.getId());
        String string = objectMapper.writeValueAsString(createTestPostRequest);

        mockMvc.perform(post("/post/upload")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(string)
                        .session(trainerSession))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value(createTestPostRequest.getTitle()))
                .andExpect(jsonPath("$.likes").value(0))
                .andExpect(jsonPath("$.commentCount").value(0));

    }
//
//    @Test
//    @DisplayName("TrainerId로 post 조회하기 200")
//    public void testPostsByTrainer200() throws Exception {
//
//        Long trainerId = 1L;
//        List<Post> posts = Arrays.asList(new Post(), new Post()); // 샘플 포스트 생성
//
//        // postService.getByTrainer() 메서드의 동작을 모킹합니다.
//        when(postService.getByTrainer(trainerId)).thenReturn(posts);
//
//        // "/list/{trainerId}" 엔드포인트에 대한 GET 요청을 수행합니다.
//        mockMvc.perform(get("/list/{trainerId}", trainerId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()", is(posts.size())))
//                .andExpect(jsonPath("$[0].id").exists())
//                .andExpect(jsonPath("$[0].title").exists())
//                .andExpect(jsonPath("$[1].id").exists())
//                .andExpect(jsonPath("$[1].title").exists());
//
//        // postService.getByTrainer() 메서드가 올바른 trainerId로 호출되었는지 검증합니다.
//        verify(postService).getByTrainer(trainerId);
//    }

}