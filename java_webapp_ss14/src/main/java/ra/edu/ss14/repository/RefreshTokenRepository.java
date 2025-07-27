package ra.edu.ss14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss14.model.dto.entity.RefreshToken;
import ra.edu.ss14.model.dto.entity.User;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
}

