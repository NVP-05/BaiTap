package ra.edu.ss13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.ss13.model.entity.Role;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleName(String roleName);
}