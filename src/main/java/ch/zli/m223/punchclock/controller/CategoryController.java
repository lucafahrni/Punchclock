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
    private CategoryService categoryService;
    private UserDetailsServiceImpl userDetailsService;

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
            if(!applicationUser.getRole().equals("admin")) throw new BadRequestException();
            return categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long id, Principal principal) {
        User applicationUser = userDetailsService.getUserByUsername(principal.getName());
        if(!applicationUser.getRole().equals("admin")) throw new BadRequestException();
        categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category updateCategory(@RequestBody Category category, Principal principal) {
        User applicationUser = userDetailsService.getUserByUsername(principal.getName());
        if (!applicationUser.getRole().equals("admin")) throw new BadRequestException();
        return categoryService.updateCategory(category);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }
}

