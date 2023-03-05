package project.healthcommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.domain.CategoryPost;
import project.healthcommunity.domain.Post;
import project.healthcommunity.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글 등록
     */
    @Transactional
    public void join(Post post){
        postRepository.save(post);
    }

    /**
     * 수정
     */
    @Transactional
    public void update(Long id, String title, String content, List<CategoryPost> categoryList){
        Post post = findOne(id);
        post.update(title, content, categoryList);
    }

    /**
     * 조회
     */
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
}
