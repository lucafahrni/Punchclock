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

    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }
    public User updateUser(User user) { return userRepository.save(user); }
    public void deleteEntry(Long id) { userRepository.deleteById(id); }
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
