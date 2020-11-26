package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.EntryRepository;
import ch.zli.m223.punchclock.repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //User wird erstellt und gesaved
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }
    //User wird geupdated und gesaved
    public User updateUser(User user) { return userRepository.save(user); }
    //User wird gelöscht
    public void deleteEntry(Long id) { userRepository.deleteById(id); }
    //Alle User
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
