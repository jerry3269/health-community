package project.healthcommunity.category.repository;

import project.healthcommunity.category.domain.Category;

public interface CategoryRepositoryCustom {

    Category getById(Long categoryId);
}
