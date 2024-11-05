package pe.edu.upc.center.booksphere.users.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.booksphere.users.domain.model.aggregates.User;
import pe.edu.upc.center.booksphere.users.domain.model.commands.CreateUserCommand;
import pe.edu.upc.center.booksphere.users.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.center.booksphere.users.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.center.booksphere.users.domain.services.UserCommandService;
import pe.edu.upc.center.booksphere.users.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateUserCommand command) {
        var email = command.email();
        if (this.userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }
        var user = new User(command);
        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user: " + e.getMessage());
        }
        return user.getId();
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        var userId = command.userId();
        var email = command.email();
        if (this.userRepository.existsByEmailAndIdIsNot(email, userId)) {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }

        if (!this.userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }

        var userToUpdate = this.userRepository.findById(userId).get();
        userToUpdate.updateInformation(command.name(), command.birthday(), command.gender(), command.favoriteBook(), command.registerDate(), command.email(), command.description(), command.library());

        try {
            var updatedUser = this.userRepository.save(userToUpdate);
            return Optional.of(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating user: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteUserCommand command) {
        if (!this.userRepository.existsById(command.userId())) {
            throw new IllegalArgumentException("User with id " + command.userId() + " does not exist");
        }

        try {
            this.userRepository.deleteById(command.userId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting user: " + e.getMessage());
        }
    }

    @Override
    public Long handle(Record record) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}