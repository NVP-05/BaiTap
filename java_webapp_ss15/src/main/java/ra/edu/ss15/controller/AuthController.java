package ra.edu.ss15.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss15.model.dto.entity.Role;
import ra.edu.ss15.model.dto.entity.User;
import ra.edu.ss15.model.dto.request.RefreshTokenRequest;
import ra.edu.ss15.model.dto.request.UserLogin;
import ra.edu.ss15.model.dto.request.UserRegister;
import ra.edu.ss15.model.dto.response.APIResponse;
import ra.edu.ss15.model.dto.response.JWTResponse;
import ra.edu.ss15.model.dto.response.UserResponse;
import ra.edu.ss15.service.UserService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<User>> registerUser(@RequestBody UserRegister userRegister) {
        User user = userService.registerUser(userRegister);
        return new ResponseEntity<>(
                new APIResponse<>(true, "Register user successfully!", user, HttpStatus.CREATED),
                HttpStatus.CREATED
        );
    }

    // Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<APIResponse<JWTResponse>> login(@RequestBody UserLogin userLogin) {
        JWTResponse jwtResponse = userService.login(userLogin);
        return new ResponseEntity<>(
                new APIResponse<>(true, "Login successfully!", jwtResponse, HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<APIResponse<JWTResponse>> refreshToken(@RequestBody RefreshTokenRequest request,
                                                                 HttpServletRequest servletRequest) {
        String ip = servletRequest.getRemoteAddr();
        JWTResponse jwtResponse = userService.refreshToken(request.getRefreshToken(), ip);
        return new ResponseEntity<>(
                new APIResponse<>(true, "Refresh token success!", jwtResponse, HttpStatus.OK),
                HttpStatus.OK
        );
    }


    @PostMapping("/logout")
    public ResponseEntity<APIResponse<String>> logout(HttpServletRequest request) {
        String token = extractTokenFromHeader(request);
        userService.logout(token);
        return new ResponseEntity<>(
                new APIResponse<>(true, "Logout successfully", "Token revoked", HttpStatus.OK),
                HttpStatus.OK
        );
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/role")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestParam Role role) {
        userService.updateUserRole(id, role);
        return ResponseEntity.ok("Updated role successfully.");
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUserInfo() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(new UserResponse(user));
    }
}
