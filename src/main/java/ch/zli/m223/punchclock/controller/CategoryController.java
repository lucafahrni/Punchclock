package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.domain.UserDetailsServiceImpl;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import java.security.Principal;
import java.util.List;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class CategoryController
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public CategoryService categoryService() {
        return categoryService;
    }

    public CategoryController(@Lazy CategoryService categoryService   , UserDetailsServiceImpl userDetailsService) {
        this.categoryService = categoryService;
        this.userDetailsService = userDetailsService;

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category, Principal principal) {
            User applicationUser = userDetailsService.getUserByUsername(principal.getName());
            return categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long id, Principal principal) {
        User applicationUser = userDetailsService.getUserByUsername(principal.getName());
        categoryService.deleteCategory(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category updateCategory(@RequestBody Category category, Principal principal) {
        User applicationUser = userDetailsService.getUserByUsername(principal.getName());
        return categoryService.updateCategory(category);
    }
}

