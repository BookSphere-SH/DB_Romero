package pe.edu.upc.center.booksphere.profiles.domain.services;
import pe.edu.upc.center.booksphere.profiles.domain.model.aggregates.Author;
import pe.edu.upc.center.booksphere.profiles.domain.model.commands.CreateAuthorCommand;
import pe.edu.upc.center.booksphere.profiles.domain.model.commands.DeleteAuthorCommand;
import pe.edu.upc.center.booksphere.profiles.domain.model.commands.UpdateAuthorCommand;

import java.util.Optional;

public interface AuthorCommandService {
    Long handle(CreateAuthorCommand command);
    Optional<Author> handle(UpdateAuthorCommand command);
    void handle(DeleteAuthorCommand command);
}