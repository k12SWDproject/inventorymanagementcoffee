package swd.SWDProject.config.sercurity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import swd.SWDProject.entity.User;
import swd.SWDProject.repository.UserRepository;

import java.util.ArrayList;

import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    //private final AuthorityRepository authorityRepository;


    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authorities);
    }

}
