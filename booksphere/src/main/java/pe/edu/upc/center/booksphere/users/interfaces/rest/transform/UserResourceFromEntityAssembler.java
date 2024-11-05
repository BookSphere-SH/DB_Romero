package pe.edu.upc.center.booksphere.users.interfaces.rest.transform;
import pe.edu.upc.center.booksphere.users.domain.model.aggregates.User;
import pe.edu.upc.center.booksphere.users.interfaces.rest.resources.ProfileResource;

public class UserResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(User entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getFullName(),
                entity.getBirthday(),
                entity.getGender(),
                entity.getFavoriteBook(),
                entity.getRegisterDate(),
                entity.getEmail(),
                entity.getDescription(),
                null, // Publisher is not applicable for User
                null, // Books are not applicable for User
                entity.getLibrary()
        );
    }
}
