package pe.edu.upc.center.booksphere.users.interfaces.rest.transform;

import pe.edu.upc.center.booksphere.users.domain.model.commands.CreateUserCommand;
import pe.edu.upc.center.booksphere.users.interfaces.rest.resources.CreateProfileResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateUserCommand(
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