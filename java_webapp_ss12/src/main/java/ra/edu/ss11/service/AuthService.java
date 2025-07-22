package ra.edu.ss11.service;

import ra.edu.ss11.model.dto.request.LoginRequest;
import ra.edu.ss11.model.dto.request.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
    String login(LoginRequest request);
}
