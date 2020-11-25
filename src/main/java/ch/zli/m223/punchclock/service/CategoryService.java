package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.CategoryRepository;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class CategoryService
 */
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) { return categoryRepository.saveAndFlush(category); }
    public Category updateCategory(Category category) { return categoryRepository.save(category); }
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
