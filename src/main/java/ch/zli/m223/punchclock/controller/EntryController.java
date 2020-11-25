package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.domain.UserDetailsServiceImpl;
import ch.zli.m223.punchclock.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import java.security.Principal;
import java.util.List;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class EntryController
 */
@RestController
@RequestMapping("/entries")
public class EntryController {
    private EntryService entryService;
    private UserDetailsServiceImpl userDetailsService;

    public EntryController(EntryService entryService, UserDetailsServiceImpl userDetailsService) {
        this.entryService = entryService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createCategory(@RequestBody Entry entry, Principal principal) {
        User applicationUser = userDetailsService.getUserByUsername(principal.getName());
        if(!applicationUser.getRole().equals("admin")) throw new BadRequestException();
        return entryService.createEntry(entry);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable long id, Principal principal) {
        User applicationUser = userDetailsService.getUserByUsername(principal.getName());
        if(!applicationUser.getRole().equals("admin")) throw new BadRequestException();
        entryService.deleteEntry(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entry updateEntry(@RequestBody Entry entry, Principal principal) {
        User applicationUser = userDetailsService.getUserByUsername(principal.getName());
        if (!applicationUser.getRole().equals("admin")) throw new BadRequestException();
        return entryService.updateEntry(entry);
    }
}
