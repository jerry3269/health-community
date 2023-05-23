package project.healthcommunity.comment.domain;

import jakarta.persistence.*;
import lombok.*;
import project.healthcommunity.comment.dto.UpdateCommentRequest;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.global.basic.BaseEntity;
import project.healthcommunity.member.domain.Member;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "content", "likes", "status"})
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Lob
    private String content;

    private int likes = 0;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> child = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    private Comment parent;

    @Enumerated(EnumType.STRING)
    private CommentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = true)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = true)
    private Trainer trainer;

    // 생성자

    @Builder(builderMethodName = "memberNoParentBuilder", builderClassName = "memberNoParentBuilder")
    public Comment(Post post, String content, Member member) {
        addPost(post);
        this.content = content;
        addMember(member);
        this.status = CommentStatus.CREATE;
    }

    @Builder(builderMethodName = "trainerNoParentBuilder", builderClassName = "trainerNoParentBuilder")
    public Comment(Post post, String content, Trainer trainer) {
        addPost(post);
        this.content = content;
        addTrainer(trainer);
        this.status = CommentStatus.CREATE;
    }
    @Builder(builderMethodName = "memberParentBuilder", builderClassName = "memberParentBuilder")
    public Comment(Post post, String content, Member member, Comment parent) {
        addPost(post);
        this.content = content;
        addMember(member);
        addParentComment(parent);
        this.status = CommentStatus.CREATE;
    }

    @Builder(builderMethodName = "trainerParentBuilder",builderClassName = "trainerParentBuilder")
    public Comment(Post post, String content, Trainer trainer, Comment parent) {
        addPost(post);
        this.content = content;
        addTrainer(trainer);
        addParentComment(parent);
        this.status = CommentStatus.CREATE;
    }


    // 연관관계 메서드 시작 //
    public void addPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public void addParentComment(Comment comment) {
        this.parent = comment;
        comment.getChild().add(this);
    }

    public void addMember(Member member) {
        this.member = member;
        member.getCommentList().add(this);
        member.resetCountParameters();
    }

    public void addTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getCommentList().add(this);
        trainer.resetCountParameters();
    }

    // 비지니스 로직 //

    public void update(UpdateCommentRequest updateCommentRequest) {
        this.content = updateCommentRequest.getContent();
        this.status = CommentStatus.MODIFY;
    }

    public void delete() {
        this.status = CommentStatus.DELETE;
    }
}
