package pe.edu.upc.center.booksphere.users.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.booksphere.users.domain.model.aggregates.Author;
import pe.edu.upc.center.booksphere.users.domain.model.commands.CreateAuthorCommand;
import pe.edu.upc.center.booksphere.users.domain.model.commands.DeleteAuthorCommand;
import pe.edu.upc.center.booksphere.users.domain.model.commands.UpdateAuthorCommand;
import pe.edu.upc.center.booksphere.users.domain.services.AuthorCommandService;
import pe.edu.upc.center.booksphere.users.infrastructure.persistence.jpa.repositories.AuthorRepository;

import java.util.Optional;

@Service
public class AuthorCommandServiceImpl implements AuthorCommandService {

    private final AuthorRepository authorRepository;

    public AuthorCommandServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Long handle(CreateAuthorCommand command) {
        var email = command.email();
        if (this.authorRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Author with email " + email + " already exists");
        }
        var author = new Author(command);
        try {
            this.authorRepository.save(author);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving author: " + e.getMessage());
        }
        return author.getId();
    }

    @Override
    public Optional<Author> handle(UpdateAuthorCommand command) {
        var authorId = command.authorId();
        var email = command.email();
        if (this.authorRepository.existsByEmailAndIdIsNot(email, authorId)) {
            throw new IllegalArgumentException("Author with email " + email + " already exists");
        }

        if (!this.authorRepository.existsById(authorId)) {
            throw new IllegalArgumentException("Author with id " + authorId + " does not exist");
        }

        var authorToUpdate = this.authorRepository.findById(authorId).get();
        authorToUpdate.updateInformation(command.name(), command.birthday(), command.gender(), command.favoriteBook(), command.registerDate(), command.email(), command.description(), command.publisher(), command.books());

        try {
            var updatedAuthor = this.authorRepository.save(authorToUpdate);
            return Optional.of(updatedAuthor);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating author: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteAuthorCommand command) {
        if (!this.authorRepository.existsById(command.authorId())) {
            throw new IllegalArgumentException("Author with id " + command.authorId() + " does not exist");
        }

        try {
            this.authorRepository.deleteById(command.authorId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting author: " + e.getMessage());
        }
    }

    @Override
    public Long handle(Record record) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}