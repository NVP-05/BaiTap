package ra.edu.ss15.model.dto.response;

import ra.edu.ss15.model.dto.entity.Role;
import ra.edu.ss15.model.dto.entity.User;

public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
    private Role role;

    public UserResponse(User user) {
        this.id = user.getUserId();
        this.username = user.getUsername();
        this.role = user.getRoles().get(0);
    }
}

