package project.healthcommunity.trainer.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrainer is a Querydsl query type for Trainer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrainer extends EntityPathBase<Trainer> {

    private static final long serialVersionUID = -422556102L;

    public static final QTrainer trainer = new QTrainer("trainer");

    public final project.healthcommunity.global.domain.QBaseEntity _super = new project.healthcommunity.global.domain.QBaseEntity(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Integer> career = createNumber("career", Integer.class);

    public final ListPath<project.healthcommunity.certificate.domain.Certificate, project.healthcommunity.certificate.domain.QCertificate> certificates = this.<project.healthcommunity.certificate.domain.Certificate, project.healthcommunity.certificate.domain.QCertificate>createList("certificates", project.healthcommunity.certificate.domain.Certificate.class, project.healthcommunity.certificate.domain.QCertificate.class, PathInits.DIRECT2);

    public final ListPath<project.healthcommunity.comment.domain.Comment, project.healthcommunity.comment.domain.QComment> commentList = this.<project.healthcommunity.comment.domain.Comment, project.healthcommunity.comment.domain.QComment>createList("commentList", project.healthcommunity.comment.domain.Comment.class, project.healthcommunity.comment.domain.QComment.class, PathInits.DIRECT2);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<project.healthcommunity.post.domain.Post, project.healthcommunity.post.domain.QPost> postList = this.<project.healthcommunity.post.domain.Post, project.healthcommunity.post.domain.QPost>createList("postList", project.healthcommunity.post.domain.Post.class, project.healthcommunity.post.domain.QPost.class, PathInits.DIRECT2);

    public final NumberPath<Integer> ranking = createNumber("ranking", Integer.class);

    public final StringPath trainerName = createString("trainerName");

    public QTrainer(String variable) {
        super(Trainer.class, forVariable(variable));
    }

    public QTrainer(Path<? extends Trainer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrainer(PathMetadata metadata) {
        super(Trainer.class, metadata);
    }

}

