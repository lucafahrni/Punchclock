package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class EntryService
 */
@Service
public class EntryService {
    private EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }
    //Entry wird erstellt und gesaved
    public Entry createEntry(Entry entry) {
        return entryRepository.saveAndFlush(entry);
    }
    //Entry wird geupdated und gesaved
    public Entry updateEntry(Entry entry) { return entryRepository.save(entry); }
    //Alle Entries
    public List<Entry> findAll() {
        return entryRepository.findAll();
    }
    //Entry wird gelöscht
    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }
}
