package ra.edu.ss11.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.edu.ss11.service.CustomUserDetailsService;

import java.util.List;

@Service
public class CustomUserDetailsServiceImp implements CustomUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return new org.springframework.security.core.userdetails.User(
                    "admin",
                    passwordEncoder.encode("123456"),
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        } else if ("user".equals(username)) {
            return new org.springframework.security.core.userdetails.User(
                    "user",
                    passwordEncoder.encode("123456"),
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
        }

        throw new UsernameNotFoundException("User not found");
    }
}