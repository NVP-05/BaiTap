package ra.edu.ss15.security.pricipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.edu.ss15.model.dto.entity.Role;
import ra.edu.ss15.model.dto.entity.User;
import ra.edu.ss15.repository.UserRepository;


import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Khong ton tai username"));
        return CustomUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .authorities(mapRoleToGrandAuthorities(user.getRoles()))
                .build();
    }

    private Collection<? extends GrantedAuthority> mapRoleToGrandAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .toList();
    }

}