package project.healthcommunity.global.domain;

import project.healthcommunity.category.domain.Category;

public class StaticField {
    public static final Category rootCategory
            = Category.noParentBuilder()
            .categoryName("운동")
            .build();

}
