package pe.edu.upc.center.booksphere.users.interfaces.rest.transform;

import pe.edu.upc.center.booksphere.users.domain.model.commands.UpdateAuthorCommand;
import pe.edu.upc.center.booksphere.users.interfaces.rest.resources.ProfileResource;

public class UpdateAuthorCommandFromResourceAssembler {
    public static UpdateAuthorCommand toCommandFromResource(Long authorId, ProfileResource resource) {
        return new UpdateAuthorCommand(
                authorId,
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