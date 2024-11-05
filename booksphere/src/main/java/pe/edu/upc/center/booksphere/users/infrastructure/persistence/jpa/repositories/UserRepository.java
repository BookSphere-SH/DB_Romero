package pe.edu.upc.center.booksphere.users.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.center.booksphere.users.domain.model.aggregates.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdIsNot(String email, Long id);
    Optional<User> findByFullName(String fullName);
}