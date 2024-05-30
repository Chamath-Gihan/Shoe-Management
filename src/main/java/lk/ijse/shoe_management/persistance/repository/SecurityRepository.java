package lk.ijse.shoe_management.persistance.repository;

import lk.ijse.shoe_management.persistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityRepository extends JpaRepository<User,String> {
    
    Optional<User> findByEmail(String email);
}
