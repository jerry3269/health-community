package project.healthcommunity.global.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.dto.CreatePostRequest;
import project.healthcommunity.post.dto.PostSearchCond;
import project.healthcommunity.post.dto.UpdatePostRequest;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.util.ControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.healthcommunity.global.error.ErrorStaticField.*;

class PostControllerTest extends ControllerTest {

    @Test
    @DisplayName("Post list 조회 성공 200")
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

    @Test
    @DisplayName("TrainerId로 post 조회하기 200")
    void testPostsByTrainer200() throws Exception {
        Trainer testTrainer = createAndSaveTestTrainer();
        Post testPost = createAndSaveTestPost(testTrainer);

        mockMvc.perform(get("/post/list/{trainerId}", testTrainer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].title").value(testPost.getTitle()))
                .andExpect(jsonPath("$[0].likes").value(testPost.getLikes()))
                .andExpect(jsonPath("$[0].commentCount").value(0));
    }

    @Test
    @DisplayName("올바른 사용자가 post update를 시도할때 성공 200")
    void update200() throws Exception {
        Trainer testTrainer = createAndSaveTestTrainer();
        MockHttpSession trainerSession = getTrainerSession(testTrainer);
        Post testPost = createAndSaveTestPost(testTrainer);

        UpdatePostRequest updatePostRequest = UpdatePostRequest.builder()
                .postId(testPost.getId())
                .title("update_title")
                .content("update_content")
                .build();

        String string = objectMapper.writeValueAsString(updatePostRequest);

        mockMvc.perform(patch("/post/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(trainerSession)
                        .content(string))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("update_title"))
                .andExpect(jsonPath("$.likes").value(0))
                .andExpect(jsonPath("$.commentCount").value(0));
    }

    @Test
    @DisplayName("권한이 없는 사용자가 post update를 시도할때 실패 401")
    void update401() throws Exception {
        Trainer unAuthorizedTrainer = createAndSaveTestTrainer();
        MockHttpSession unAuthorizedTrainerSession = getTrainerSession(unAuthorizedTrainer);

        Trainer realTrainer = createAndSaveRealTrainer();
        Post testPost = createAndSaveTestPost(realTrainer);

        UpdatePostRequest updatePostRequest = UpdatePostRequest.builder()
                .postId(testPost.getId())
                .title("update_title")
                .content("update_content")
                .build();

        String string = objectMapper.writeValueAsString(updatePostRequest);

        mockMvc.perform(patch("/post/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(unAuthorizedTrainerSession)
                        .content(string))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.errorCode").value(UNAUTHORIZED))
                .andExpect(jsonPath("$.message").value(POST_UNAUTHORIZED));
    }

    @Test
    @DisplayName("올바른 TrainerSession이 들어왔을때 post delete 성공 200")
    void delete200() throws Exception {
        Trainer testTrainer = createAndSaveTestTrainer();
        MockHttpSession trainerSession = getTrainerSession(testTrainer);
        Post testPost = createAndSaveTestPost(testTrainer);

        mockMvc.perform(delete("/post/delete/{postId}", testPost.getId())
                        .session(trainerSession))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("searchCond으로 post조회 성공 200")
    void search200() throws Exception {
        Trainer testTrainer = createAndSaveTestTrainer();
        Post testPost = createAndSaveTestPost(testTrainer);

        mockMvc.perform(get("/post/list/search")
                .queryParam("likesGoe", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is((testPost.getId().intValue()))))
                .andExpect(jsonPath("$.content[0].title", is(testPost.getTitle())))
                .andExpect(jsonPath("$.content[0].likes", is(0)))
                .andExpect(jsonPath("$.content[0].commentCount", is(0)))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.size", is(10)));
    }
}