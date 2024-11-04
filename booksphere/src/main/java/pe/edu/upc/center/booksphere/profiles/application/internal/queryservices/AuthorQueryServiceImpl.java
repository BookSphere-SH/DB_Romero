package pe.edu.upc.center.booksphere.profiles.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.booksphere.profiles.domain.model.aggregates.Author;
import pe.edu.upc.center.booksphere.profiles.domain.model.queries.GetAllProfilesQuery;
import pe.edu.upc.center.booksphere.profiles.domain.model.queries.GetProfileByFullNameQuery;
import pe.edu.upc.center.booksphere.profiles.domain.model.queries.GetProfileByIdQuery;
import pe.edu.upc.center.booksphere.profiles.domain.services.AuthorQueryService;
import pe.edu.upc.center.booksphere.profiles.infrastructure.persistence.jpa.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorQueryServiceImpl implements AuthorQueryService {

    private final AuthorRepository authorRepository;

    public AuthorQueryServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> handleAuthorQuery(GetAllProfilesQuery query) {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> handleAuthorQuery(GetProfileByIdQuery query) {
        return this.authorRepository.findById(query.id());
    }

    @Override
    public Optional<Author> handleAuthorQuery(GetProfileByFullNameQuery query) {
        return this.authorRepository.findByFullName(query.fullName());
    }
}