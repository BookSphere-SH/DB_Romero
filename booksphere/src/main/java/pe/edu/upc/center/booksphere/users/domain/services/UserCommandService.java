package pe.edu.upc.center.booksphere.users.domain.services;

import pe.edu.upc.center.booksphere.users.domain.model.commands.CreateUserCommand;
import pe.edu.upc.center.booksphere.users.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.center.booksphere.users.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.center.booksphere.users.domain.model.aggregates.User;

import java.util.Optional;

public interface UserCommandService {
    Long handle(CreateUserCommand command);
    Optional<User> handle(UpdateUserCommand command);
    void handle(DeleteUserCommand command);
    Long handle(Record record);
}