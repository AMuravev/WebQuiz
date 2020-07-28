package engine.service;

import engine.Utils.AuthenticationFacade;
import engine.security.UserPrincipalSecurity;
import engine.entiry.User;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserPrincipalSecurity(user);
    }

    public User getCurrentAuthUser() throws UsernameNotFoundException {
        Object principal = authenticationFacade.getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipalSecurity) {
            return ((UserPrincipalSecurity) principal).getUser();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User save(User user) {
        User user1 = userRepository.findByEmail(user.getEmail());
        if (user1 != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        return userRepository.save(user);
    }
}
