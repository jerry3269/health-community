package project.healthcommunity.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.service.CategoryService;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.categorypost.service.CategoryPostService;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.dto.CreatePostRequest;
import project.healthcommunity.post.dto.DeletePostRequest;
import project.healthcommunity.post.dto.PostResponse;
import project.healthcommunity.post.dto.UpdatePostRequest;
import project.healthcommunity.post.exception.PostNotAllowedException;
import project.healthcommunity.post.repository.PostRepositoryCustom;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.domain.TrainerSession;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepositoryCustom postRepositoryCustom;
    private final TrainerService trainerService;
    private final CategoryPostService categoryPostService;
    private final CategoryService categoryService;

    @Transactional
    public PostResponse post(TrainerSession trainerSession, CreatePostRequest createPostRequest){
        Trainer trainer = trainerService.findOne(createPostRequest.getTrainerId());

        List<Category> categoryList = createPostRequest.getCategoryNameList().stream()
                .map(name -> categoryService.categoryListByName(name).get(0))
                .collect(toList());

        Post post = new Post(createPostRequest.getTitle(), createPostRequest.getContent(), categoryList, trainer);

        List<CategoryPost> categoryPostList = post.getCategoryList();
        for (CategoryPost categoryPost : categoryPostList) {
            categoryPostService.save(categoryPost);
        }
        postRepositoryCustom.save(post);
        return new PostResponse(post);
    }

    @Transactional
    public PostResponse update(TrainerSession trainerSession, UpdatePostRequest updatePostRequest){
        Post post = getById(updatePostRequest.getPostId());

        if (isValidUser(trainerSession, post)) {
            post.update(updatePostRequest);
            return new PostResponse(post);
        }
        throw new PostNotAllowedException();
    }

    @Transactional
    public void delete(TrainerSession trainerSession, DeletePostRequest deletePostRequest) {
        Post post = getById(deletePostRequest.getPostId());

        if(isValidUser(trainerSession, post)){
            categoryPostService.deleteByPostId(deletePostRequest.getPostId());
            postRepositoryCustom.deleteById(deletePostRequest.getPostId());
            return;
        }
        throw new PostNotAllowedException();
    }

    private boolean isValidUser(TrainerSession trainerSession, Post post) {
        return (post.getTrainer().getId() == trainerSession.getId()) ? true : false;
    }

    public Post getById(Long id) {
        return postRepositoryCustom.getById(id);
    }
    public List<Post> posts(){
        return postRepositoryCustom.findAll();
    }
    public List<Post> findByTrainer(Long trainerId) {
        return postRepositoryCustom.findByTrainer_Id(trainerId);
    }
}
