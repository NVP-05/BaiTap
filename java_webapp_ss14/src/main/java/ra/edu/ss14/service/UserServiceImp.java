package ra.edu.ss14.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.edu.ss14.model.dto.entity.RoleName;
import ra.edu.ss14.model.dto.request.UserLogin;
import ra.edu.ss14.model.dto.request.UserRegister;
import ra.edu.ss14.model.dto.response.JWTResponse;
import ra.edu.ss14.model.dto.entity.Role;
import ra.edu.ss14.model.dto.entity.User;
import ra.edu.ss14.repository.RoleRepository;
import ra.edu.ss14.repository.UserRepository;
import ra.edu.ss14.security.jwt.JWTProvider;
import org.springframework.security.core.Authentication;
import ra.edu.ss14.security.pricipal.CustomUserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User registerUser(UserRegister userRegister) {
        User user = User.builder()
                .username(userRegister.getUsername())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .email(userRegister.getEmail())
                .roles(mapRoleStringToRole(userRegister.getRoles()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Không tồn tại username: " + username));
    }

    @Override
    public JWTResponse login(UserLogin userLogin) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        } catch (AuthenticationException e) {
            log.error("Sai username hoặc password!");
            throw e;
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userDetails.getUsername());

        return JWTResponse.builder()
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .authorities(userDetails.getAuthorities())
                .token(token)
                .build();
    }

    @Override
    public JWTResponse refreshToken(String refreshToken, String ipAddress) {
        String username = jwtProvider.getUsernameFromToken(refreshToken);
        if (username == null || !jwtProvider.validateToken(refreshToken)) {
            log.error("Refresh token không hợp lệ hoặc đã hết hạn!");
            return null;
        }

        String newAccessToken = jwtProvider.refreshToken(refreshToken, username);
        if (newAccessToken == null) {
            log.error("Không thể làm mới access token!");
            return null;
        }

        User user = getByUsername(username);
        return JWTResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .authorities((Collection<? extends GrantedAuthority>) user.getRoles())
                .token(newAccessToken)
                .build();
    }

    @Override
    public void logout(String accessToken) {
        if (accessToken == null || !jwtProvider.validateToken(accessToken)) {
            log.error("Access token không hợp lệ hoặc đã hết hạn!");
            return;
        }

        String username = jwtProvider.getUsernameFromToken(accessToken);
        if (username == null) {
            log.error("Không thể lấy username từ access token!");
            return;
        }

        log.info("Người dùng {} đã đăng xuất thành công.", username);
    }

    private List<Role> mapRoleStringToRole(List<String> roles) {
        List<Role> roleList = new ArrayList<>();

        if (roles != null && !roles.isEmpty()) {
            for (String roleStr : roles) {
                RoleName roleName;
                try {
                    roleName = RoleName.valueOf(roleStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    log.warn("Role không hợp lệ: {}. Gán mặc định ROLE_USER", roleStr);
                    roleName = RoleName.ROLE_USER;
                }

                RoleName finalRoleName = roleName;
                Role role = roleRepository.findByRoleName(roleName)
                        .orElseThrow(() -> new NoSuchElementException("Không tồn tại role: " + finalRoleName));
                roleList.add(role);
            }
        } else {
            Role role = roleRepository.findByRoleName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new NoSuchElementException("Không tồn tại role_user"));
            roleList.add(role);
        }
        return roleList;
    }
}
