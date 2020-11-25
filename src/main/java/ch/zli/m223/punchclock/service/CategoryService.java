package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.CategoryRepository;
import ch.zli.m223.punchclock.user.ApplicationUser;
import org.jvnet.hk2.annotations.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        //SaveAndFlush flushes the date immediately during the excecution
        return category.saveAndFlush(category);
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Entry> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Entry> findById(Long id) {
        return categoryRepository.findById(id);
    }
}
