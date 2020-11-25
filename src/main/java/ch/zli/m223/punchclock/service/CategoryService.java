package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.repository.CategoryRepository;

import java.util.List;

public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        //SaveAndFlush flushes the date immediately during the excecution
        return category.saveAndFlush(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
