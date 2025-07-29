package ra.edu.ss15.service;


import ra.edu.ss15.model.dto.entity.Role;
import ra.edu.ss15.model.dto.entity.User;
import ra.edu.ss15.model.dto.request.UserLogin;
import ra.edu.ss15.model.dto.request.UserRegister;
import ra.edu.ss15.model.dto.response.JWTResponse;

public interface UserService {

    User registerUser(UserRegister userRegister);

    User getByUsername(String username);

    JWTResponse login(UserLogin userLogin);

    JWTResponse refreshToken(String refreshToken, String ipAddress);

    void logout(String accessToken);

    User getCurrentUser();
    void updateUserRole(Long id, Role newRole);
}
