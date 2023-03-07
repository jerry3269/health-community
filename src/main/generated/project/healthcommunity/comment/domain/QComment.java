package project.healthcommunity.comment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 211911930L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final project.healthcommunity.global.domain.QBaseEntity _super = new project.healthcommunity.global.domain.QBaseEntity(this);

    public final ListPath<Comment, QComment> child = this.<Comment, QComment>createList("child", Comment.class, QComment.class, PathInits.DIRECT2);

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

    public final project.healthcommunity.member.domain.QMember member;

    public final QComment parent;

    public final project.healthcommunity.post.domain.QPost post;

    public final EnumPath<CommentStatus> status = createEnum("status", CommentStatus.class);

    public final NumberPath<Integer> sympathy = createNumber("sympathy", Integer.class);

    public final project.healthcommunity.trainer.domain.QTrainer trainer;

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new project.healthcommunity.member.domain.QMember(forProperty("member")) : null;
        this.parent = inits.isInitialized("parent") ? new QComment(forProperty("parent"), inits.get("parent")) : null;
        this.post = inits.isInitialized("post") ? new project.healthcommunity.post.domain.QPost(forProperty("post"), inits.get("post")) : null;
        this.trainer = inits.isInitialized("trainer") ? new project.healthcommunity.trainer.domain.QTrainer(forProperty("trainer")) : null;
    }

}

