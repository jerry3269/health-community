package project.healthcommunity.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1143854790L;

    public static final QMember member = new QMember("member1");

    public final project.healthcommunity.global.domain.QBaseEntity _super = new project.healthcommunity.global.domain.QBaseEntity(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

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

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

