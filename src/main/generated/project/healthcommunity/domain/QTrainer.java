package project.healthcommunity.domain;

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

    private static final long serialVersionUID = -1732687391L;

    public static final QTrainer trainer = new QTrainer("trainer");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Integer> career = createNumber("career", Integer.class);

    public final ListPath<Certificate, QCertificate> certificates = this.<Certificate, QCertificate>createList("certificates", Certificate.class, QCertificate.class, PathInits.DIRECT2);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<Post, QPost> postList = this.<Post, QPost>createList("postList", Post.class, QPost.class, PathInits.DIRECT2);

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

