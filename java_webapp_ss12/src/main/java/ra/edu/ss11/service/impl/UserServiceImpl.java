package ra.edu.ss11.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.edu.ss11.model.dto.request.LoginRequest;
import ra.edu.ss11.model.dto.request.RegisterRequest;
import ra.edu.ss11.model.entity.User;
import ra.edu.ss11.repository.UserRepository;
import ra.edu.ss11.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import java.util.Set;
import org.springframework.security.authentication.AuthenticationManager;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements AuthService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username đã tồn tại!";
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email đã tồn tại!";
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(true)
                .roles(Set.of())
                .build();

        userRepository.save(user);
        return "Đăng ký thành công!";
    }

    @Override
    public String login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            return "Đăng nhập thành công!";
        } catch (AuthenticationException e) {
            return "Sai tài khoản hoặc mật khẩu!";
        }
    }
}