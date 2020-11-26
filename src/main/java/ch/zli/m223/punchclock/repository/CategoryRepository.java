package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class CategoryRepository
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT id FROM Category ")
    List<Entry> findAllCategories(Sort sort);
}
