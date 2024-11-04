package pe.edu.upc.center.booksphere.profiles.domain.services;
import pe.edu.upc.center.booksphere.profiles.domain.model.aggregates.User;
import pe.edu.upc.center.booksphere.profiles.domain.model.commands.CreateUserCommand;
import pe.edu.upc.center.booksphere.profiles.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.center.booksphere.profiles.domain.model.commands.UpdateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Long handle(CreateUserCommand command);
    Optional<User> handle(UpdateUserCommand command);
    void handle(DeleteUserCommand command);
}