package ra.edu.ss14.service;

import ra.edu.ss14.model.dto.request.UserLogin;
import ra.edu.ss14.model.dto.request.UserRegister;
import ra.edu.ss14.model.dto.response.JWTResponse;
import ra.edu.ss14.model.dto.entity.User;

public interface UserService {

    User registerUser(UserRegister userRegister);

    User getByUsername(String username);

    JWTResponse login(UserLogin userLogin);

    JWTResponse refreshToken(String refreshToken, String ipAddress);

    void logout(String accessToken);
}
