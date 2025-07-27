package ra.edu.ss14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.ss14.model.dto.entity.Role;
import ra.edu.ss14.model.dto.entity.RoleName;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
    boolean existsByRoleName(RoleName roleName);
}