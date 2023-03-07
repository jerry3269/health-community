package project.healthcommunity.categorypost.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategoryPost is a Querydsl query type for CategoryPost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryPost extends EntityPathBase<CategoryPost> {

    private static final long serialVersionUID = 366450542L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategoryPost categoryPost = new QCategoryPost("categoryPost");

    public final project.healthcommunity.global.domain.QBaseEntity _super = new project.healthcommunity.global.domain.QBaseEntity(this);

    public final project.healthcommunity.category.domain.QCategory category;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final project.healthcommunity.post.domain.QPost post;

    public QCategoryPost(String variable) {
        this(CategoryPost.class, forVariable(variable), INITS);
    }

    public QCategoryPost(Path<? extends CategoryPost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategoryPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategoryPost(PathMetadata metadata, PathInits inits) {
        this(CategoryPost.class, metadata, inits);
    }

    public QCategoryPost(Class<? extends CategoryPost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new project.healthcommunity.category.domain.QCategory(forProperty("category"), inits.get("category")) : null;
        this.post = inits.isInitialized("post") ? new project.healthcommunity.post.domain.QPost(forProperty("post"), inits.get("post")) : null;
    }

}

