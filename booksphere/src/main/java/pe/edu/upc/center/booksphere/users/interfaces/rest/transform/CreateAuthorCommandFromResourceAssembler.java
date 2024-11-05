package pe.edu.upc.center.booksphere.users.interfaces.rest.transform;

import pe.edu.upc.center.booksphere.users.domain.model.commands.CreateAuthorCommand;
import pe.edu.upc.center.booksphere.users.interfaces.rest.resources.CreateProfileResource;

public class CreateAuthorCommandFromResourceAssembler {
    public static CreateAuthorCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateAuthorCommand(
                resource.fullName(),
                resource.birthday(),
                resource.gender(),
                resource.favoriteBook(),
                resource.registerDate(),
                resource.email(),
                resource.description(),
                resource.publisher(),
                resource.books()
        );
    }
}