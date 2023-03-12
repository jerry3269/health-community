package project.healthcommunity.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.categorypost.repository.CategoryPostRepository;
import project.healthcommunity.post.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final CategoryPostRepository categoryPostRepository;


    @Transactional
    public Long post(Post post){
        List<CategoryPost> categoryPostList = post.getCategoryList();
        for (CategoryPost categoryPost : categoryPostList) {
            categoryPostRepository.save(categoryPost);
        }
        postRepository.save(post);
        return post.getId();
    }


    @Transactional
    public void update(Long id, String title, String content){
        Post post = findOne(id);
        post.update(title, content);
    }


    public Post findOne(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if(!optionalPost.isPresent()){
            throw new IllegalStateException("존재하지 않는 포스트입니다.");
        }
        return optionalPost.get();
    }

    /**
     * 전체 조회
     */
    public List<Post> posts(){
        return postRepository.findAll();
    }

    public void deleteCategoryPostById(Long id) {
        categoryPostRepository.deleteByPost_id(id);
    }

    public List<Post> findByTitle(String title){
        return postRepository.findByTitle(title);
    }

    public List<Post> findByMember(Long memberId){
        return postRepository.findByMember_Id(memberId);
    }

    public List<Post> findByTrainer(Long trainerId) {
        return postRepository.findByTrainer_Id(trainerId);
    }
}
