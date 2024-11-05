package pe.edu.upc.center.booksphere.users.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.center.booksphere.users.domain.model.aggregates.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdIsNot(String email, Long id);
    Optional<Author> findByFullName(String fullName);
}