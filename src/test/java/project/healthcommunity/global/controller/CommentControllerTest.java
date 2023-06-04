package project.healthcommunity.global.controller;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.comment.dto.member.CreateMemberCommentRequest;
import project.healthcommunity.comment.dto.trainer.CreateTrainerCommentRequest;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.util.ControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommentControllerTest extends ControllerTest {

    private Trainer testTrainer;
    private Post testPost;
    private Member testMember;

    private void initialComment(){
        testTrainer = createAndSaveTestTrainer();
        testPost = createAndSaveTestPost(testTrainer);
        testMember = createAndSaveTestMember();
    }

    @Test
    @DisplayName("로그인한 memeber 댓글 달기 성공 200")
    void saveMemberComment200() throws Exception {
        initialComment();

        CreateMemberCommentRequest createMemberCommentRequest = CreateMemberCommentRequest.builder()
                .memberId(testMember.getId())
                .postId(testPost.getId())
                .content(TEST_CONTENT).build();

        String string = objectMapper.writeValueAsString(createMemberCommentRequest);
        MockHttpSession memberSession = getMemberSession(testMember);

        mockMvc.perform(post("/comment/member")
                .contentType(MediaType.APPLICATION_JSON)
                .session(memberSession)
                .content(string))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId").exists())
                .andExpect(jsonPath("$.postId", is(testPost.getId().intValue())))
                .andExpect(jsonPath("$.memberId", is(testMember.getId().intValue())))
                .andExpect(jsonPath("$.content", is(TEST_CONTENT)))
                .andExpect(jsonPath("$.likes", is(0)))
                .andExpect(jsonPath("$.commentDtoList", hasSize(0)));
    }

    @Test
    @DisplayName("로그인한 Trainer 댓글 달기 성공 200")
    void saveTrainerComment200() throws Exception {
        initialComment();

        CreateTrainerCommentRequest createTrainerCommentRequest = CreateTrainerCommentRequest.builder()
                .trainerId(testTrainer.getId())
                .postId(testPost.getId())
                .content(TEST_CONTENT).build();

        String string = objectMapper.writeValueAsString(createTrainerCommentRequest);
        MockHttpSession trainerSession = getTrainerSession(testTrainer);

        mockMvc.perform(post("/comment/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(trainerSession)
                        .content(string))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId").exists())
                .andExpect(jsonPath("$.postId", is(testPost.getId().intValue())))
                .andExpect(jsonPath("$.trainerId", is(testTrainer.getId().intValue())))
                .andExpect(jsonPath("$.content", is(TEST_CONTENT)))
                .andExpect(jsonPath("$.likes", is(0)))
                .andExpect(jsonPath("$.commentDtoList", hasSize(0)));
    }

    @Test
    @DisplayName("로그인한 memeber 댓글 update 성공 200")
    void updateMemberComment200(){

    }

    @Test
    @DisplayName("로그인한 Trainer 댓글 update 성공 200")
    void updateTrainerComment200(){

    }

    @Test
    @DisplayName("로그인한 memeber 댓글 delete 성공 200")
    void deleteMemberComment200(){

    }

    @Test
    @DisplayName("로그인한 Trainer 댓글 delete 성공 200")
    void deleteTrainerComment200(){

    }
}
