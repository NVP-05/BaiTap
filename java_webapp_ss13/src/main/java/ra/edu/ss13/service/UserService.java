package ra.edu.ss13.service;

import ra.edu.ss13.model.dto.request.UserLogin;
import ra.edu.ss13.model.dto.request.UserRegister;
import ra.edu.ss13.model.dto.response.JWTResponse;
import ra.edu.ss13.model.entity.User;

public interface UserService {
    User registerUser(UserRegister userRegister);

    JWTResponse login(UserLogin userLogin);
}