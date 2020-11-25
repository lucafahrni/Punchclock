package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class CategoryRepository
 */
public interface CategoryRepository extends JpaRepository<User, Long> {
}
