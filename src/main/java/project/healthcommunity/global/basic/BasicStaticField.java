package project.healthcommunity.global.basic;

import project.healthcommunity.category.domain.Category;

public class BasicStaticField {
    private BasicStaticField(){}
    public static final Category rootCategory
            = Category.noParentBuilder()
            .categoryName("운동")
            .build();

    public static final Category testCategory1 = Category.parentBuilder()
            .categoryName("팔굽")
            .parent(rootCategory)
            .build();
    
    public static final Category testCategory2 = Category.parentBuilder()
            .categoryName("윗몸")
            .parent(rootCategory)
            .build();
    
    public static final Category testCategory3 = Category.parentBuilder()
            .categoryName("체력")
            .parent(rootCategory)
            .build();
    public static final String LOG_ID = "logId";
    public static final String LOGIN_MEMBER = "loginMember";
    public static final String LOGIN_TRAINER = "loginTrainer";





}
