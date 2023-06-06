package project.healthcommunity.global.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import project.healthcommunity.comment.controller.CommentController;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.comment.dto.UpdateCommentRequest;
import project.healthcommunity.comment.dto.member.CreateMemberCommentRequest;
import project.healthcommunity.comment.dto.trainer.CreateTrainerCommentRequest;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.util.ControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CommentControllerTest extends ControllerTest {

    private Trainer testTrainer;
    private Post testPost;
    private Member testMember;
    private Comment testMemberComment;
    private Comment testTrainerComment;

    private void initialSave(){
        testTrainer = createAndSaveTestTrainer();
        testPost = createAndSaveTestPost(testTrainer);
        testMember = createAndSaveTestMember();
    }

    private void initialUpdateAndDelete(){
        initialSave();
        testMemberComment = new Comment(testPost, TEST_CONTENT, testMember);
        testTrainerComment = new Comment(testPost, TEST_CONTENT, testTrainer);
        commentJpaRepository.save(testMemberComment);
        commentJpaRepository.save(testTrainerComment);
    }

    @Test
    @DisplayName("로그인한 memeber 댓글 달기 성공 200")
    void saveMemberComment200() throws Exception {
        initialSave();

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
                .andExpect(jsonPath("$.commentDtoList", hasSize(0)))
                .andDo(document("comment-save/member/200"));
    }

    @Test
    @DisplayName("로그인한 Trainer 댓글 달기 성공 200")
    void saveTrainerComment200() throws Exception {
        initialSave();

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
                .andExpect(jsonPath("$.commentDtoList", hasSize(0)))
                .andDo(document("comment-save/trainer/200"));
    }

    @Test
    @DisplayName("로그인한 memeber 댓글 update 성공 200")
    void updateMemberComment200() throws Exception {
        initialUpdateAndDelete();

        UpdateCommentRequest updateContent = UpdateCommentRequest.builder()
                .commentId(testMemberComment.getId())
                .content("update_content")
                .build();

        String string = objectMapper.writeValueAsString(updateContent);
        MockHttpSession memberSession = getMemberSession(testMember);

        mockMvc.perform(patch("/comment/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(memberSession)
                        .content(string))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId", is(testMemberComment.getId().intValue())))
                .andExpect(jsonPath("$.postId", is(testPost.getId().intValue())))
                .andExpect(jsonPath("$.memberId", is(testMember.getId().intValue())))
                .andExpect(jsonPath("$.content", is("update_content")))
                .andExpect(jsonPath("$.likes", is(0)))
                .andExpect(jsonPath("$.commentDtoList", hasSize(0)))
                .andDo(document("comment-update/member/200"));
    }

    @Test
    @DisplayName("로그인한 Trainer 댓글 update 성공 200")
    void updateTrainerComment200() throws Exception{
        initialUpdateAndDelete();

        UpdateCommentRequest updateContent = UpdateCommentRequest.builder()
                .commentId(testTrainerComment.getId())
                .content("update_content")
                .build();

        String string = objectMapper.writeValueAsString(updateContent);
        MockHttpSession trainerSession = getTrainerSession(testTrainer);

        mockMvc.perform(patch("/comment/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(trainerSession)
                        .content(string))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId", is(testTrainerComment.getId().intValue())))
                .andExpect(jsonPath("$.postId", is(testPost.getId().intValue())))
                .andExpect(jsonPath("$.trainerId", is(testTrainer.getId().intValue())))
                .andExpect(jsonPath("$.content", is("update_content")))
                .andExpect(jsonPath("$.likes", is(0)))
                .andExpect(jsonPath("$.commentDtoList", hasSize(0)))
                .andDo(document("comment-update/trainer/200"));
    }

    @Test
    @DisplayName("로그인한 memeber 댓글 delete 성공 200")
    void deleteMemberComment200() throws Exception{
        initialUpdateAndDelete();
        MockHttpSession memberSession = getMemberSession(testMember);

        mockMvc.perform(delete("/comment/member/{commentId}", testMemberComment.getId())
                        .session(memberSession))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId", is(testMemberComment.getId().intValue())))
                .andExpect(jsonPath("$.postId", is(testPost.getId().intValue())))
                .andExpect(jsonPath("$.memberId", is(testMember.getId().intValue())))
                .andExpect(jsonPath("$.content", is(TEST_CONTENT)))
                .andExpect(jsonPath("$.likes", is(0)))
                .andExpect(jsonPath("$.commentDtoList", hasSize(0)))
                .andDo(document("comment-delete/member/200"));
    }

    @Test
    @DisplayName("로그인한 Trainer 댓글 delete 성공 200")
    void deleteTrainerComment200() throws Exception{
        initialUpdateAndDelete();
        MockHttpSession trainerSession = getTrainerSession(testTrainer);

        mockMvc.perform(delete("/comment/trainer/{commentId}", testTrainerComment.getId())
                        .session(trainerSession))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId", is(testTrainerComment.getId().intValue())))
                .andExpect(jsonPath("$.postId", is(testPost.getId().intValue())))
                .andExpect(jsonPath("$.trainerId", is(testTrainer.getId().intValue())))
                .andExpect(jsonPath("$.content", is(TEST_CONTENT)))
                .andExpect(jsonPath("$.likes", is(0)))
                .andExpect(jsonPath("$.commentDtoList", hasSize(0)))
                .andDo(document("comment-delete/trainer/200"));
    }
}
