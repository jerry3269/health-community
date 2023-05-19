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
    public static final String DUP_LOGIN_ID = "이미 사용중인 아이디 입니다.";
    public static final String MEMBER_UNAUTHORIZED = "Member 로그인이 필요한 서비스 입니다.";
    public static final String TRAINER_UNAUTHORIZED = "Trainer 로그인이 필요한 서비스 입니다.";
    public static final String INVALID_ID = "존재하지 않는 아이디 입니다.";
    public static final String INVALID_PASSWORD= "비밀번호가 올바르지 않습니다.";




}
