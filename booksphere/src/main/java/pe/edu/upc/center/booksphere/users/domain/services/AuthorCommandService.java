package pe.edu.upc.center.booksphere.users.domain.services;

import pe.edu.upc.center.booksphere.users.domain.model.commands.CreateAuthorCommand;
import pe.edu.upc.center.booksphere.users.domain.model.commands.DeleteAuthorCommand;
import pe.edu.upc.center.booksphere.users.domain.model.commands.UpdateAuthorCommand;
import pe.edu.upc.center.booksphere.users.domain.model.aggregates.Author;
import java.util.Optional;

public interface AuthorCommandService {
    Long handle(CreateAuthorCommand command);
    Optional<Author> handle(UpdateAuthorCommand command);
    void handle(DeleteAuthorCommand command);
    Long handle(Record record);
}