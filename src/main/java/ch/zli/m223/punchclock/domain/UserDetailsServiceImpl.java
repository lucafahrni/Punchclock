package ch.zli.m223.punchclock.domain;

import ch.zli.m223.punchclock.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class UserDetailsServiceImpl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository applicationUserRepository;

    public UserDetailsServiceImpl(UserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = applicationUserRepository.findByUsername(username);
        if(applicationUser == null) throw new UsernameNotFoundException(username);
        return applicationUser;
    }
}
