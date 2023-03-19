package project.healthcommunity.post.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -1845749166L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final project.healthcommunity.global.domain.QBaseEntity _super = new project.healthcommunity.global.domain.QBaseEntity(this);

    public final ListPath<project.healthcommunity.categorypost.domain.CategoryPost, project.healthcommunity.categorypost.domain.QCategoryPost> categoryList = this.<project.healthcommunity.categorypost.domain.CategoryPost, project.healthcommunity.categorypost.domain.QCategoryPost>createList("categoryList", project.healthcommunity.categorypost.domain.CategoryPost.class, project.healthcommunity.categorypost.domain.QCategoryPost.class, PathInits.DIRECT2);

    public final ListPath<project.healthcommunity.comment.domain.Comment, project.healthcommunity.comment.domain.QComment> comments = this.<project.healthcommunity.comment.domain.Comment, project.healthcommunity.comment.domain.QComment>createList("comments", project.healthcommunity.comment.domain.Comment.class, project.healthcommunity.comment.domain.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final EnumPath<PostStatus> status = createEnum("status", PostStatus.class);

    public final StringPath title = createString("title");

    public final project.healthcommunity.trainer.domain.QTrainer trainer;

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.trainer = inits.isInitialized("trainer") ? new project.healthcommunity.trainer.domain.QTrainer(forProperty("trainer")) : null;
    }

}

