package pe.edu.upc.center.booksphere.users.interfaces.rest.transform;

import pe.edu.upc.center.booksphere.users.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.center.booksphere.users.interfaces.rest.resources.ProfileResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long userId, ProfileResource resource) {
        return new UpdateUserCommand(
                userId,
                resource.fullName(),
                resource.birthday(),
                resource.gender(),
                resource.favoriteBook(),
                resource.registerDate(),
                resource.email(),
                resource.description(),
                resource.library()
        );
    }
}