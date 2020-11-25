package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ApplicationUser, Long> {
}
