package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.Entry;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class EntryRepository
 */
public interface EntryRepository extends JpaRepository<Entry, Long> {
}