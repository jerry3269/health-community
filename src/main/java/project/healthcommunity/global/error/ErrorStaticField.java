package project.healthcommunity.global.error;

public class ErrorStaticField {
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;

    public static final int NOT_MATCH = 405;

    public static final int OK = 200;
    public static final int CREATED = 201;


    public static final String MEMBER_NOT_FOUND = "존재하지 않는 회원입니다.";
    public static final String TRAINER_NOT_FOUND = "존재하지 않는 트레이너입니다.";
    public static final String POST_NOT_FOUND = "존재하지 않는 포스트입니다.";
    public static final String COMMENT_NOT_FOUND = "존재하지 않는 댓글입니다.";
    public static final String CERTIFICATE_NOT_FOUND = "존재하지 않는 자격증입니다.";
    public static final String CATEGORY_NOT_FOUND = "존재하지 않는 카테고리입니다.";
    public static final String DUP_LOGIN_ID = "이미 사용중인 아이디 입니다.";
    public static final String DUP_CATEGORY = "이미 해당 카테고리가 존재합니다.";
    public static final String MEMBER_UNAUTHORIZED = "Member 로그인이 필요한 서비스 입니다.";
    public static final String TRAINER_UNAUTHORIZED = "Trainer 로그인이 필요한 서비스 입니다.";
    public static final String INVALID_ID = "존재하지 않는 아이디 입니다.";
    public static final String INVALID_PASSWORD = "비밀번호가 올바르지 않습니다.";
    public static final String BINDING_ERROR = "잘못된 CreateForm 형식입니다.";
    public static final String POST_UNAUTHORIZED = "해당 post에 접근 권한이 없는 사용자입니다.";
    public static final String COMMENT_UNAUTHORIZED = "해당 comment에 접근 권한이 없는 사용자입니다.";
    public static final String CERTIFICATE_UNAUTHORIZED = "해당 certificate에 접근 권한이 없는 사용자입니다.";
    public static final String REQUEST_BODY_NOT_FOUND = "ReqeustBody에 입력된 내용이 없습니다.";
    public static final String CERTIFICATE_NOT_YET = "아직 취득한 자격증이 없습니다.";
    public static final String DUP_CERTIFICATE = "이미 해당 자격증이 있습니다.";




}
