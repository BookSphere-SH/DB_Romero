package pe.edu.upc.center.booksphere.users.interfaces.rest.transform;

import pe.edu.upc.center.booksphere.users.domain.model.aggregates.Author;
import pe.edu.upc.center.booksphere.users.interfaces.rest.resources.ProfileResource;

public class AuthorResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Author entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getFullName(),
                entity.getBirthday(),
                entity.getGender(),
                entity.getFavoriteBook(),
                entity.getRegisterDate(),
                entity.getEmail(),
                entity.getDescription(),
                entity.getPublisher(),
                entity.getBooks(),
                null // Library is not applicable for Author
        );
    }
}