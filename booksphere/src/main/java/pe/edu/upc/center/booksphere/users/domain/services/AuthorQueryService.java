package pe.edu.upc.center.booksphere.users.domain.services;

import pe.edu.upc.center.booksphere.users.domain.model.aggregates.Author;
import pe.edu.upc.center.booksphere.users.domain.model.queries.GetAllProfilesQuery;
import pe.edu.upc.center.booksphere.users.domain.model.queries.GetProfileByFullNameQuery;
import pe.edu.upc.center.booksphere.users.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AuthorQueryService {
    List<Author> handleAuthorQuery(GetAllProfilesQuery query);
    Optional<Author> handleAuthorQuery(GetProfileByIdQuery query);
    Optional<Author> handleAuthorQuery(GetProfileByFullNameQuery query);

    Optional<?> handle(GetProfileByIdQuery getProfileByIdQuery);

    Optional<?> handle(GetAllProfilesQuery getAllProfilesQuery);
}
