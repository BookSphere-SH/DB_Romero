package pe.edu.upc.center.booksphere.users.domain.services;

import pe.edu.upc.center.booksphere.users.domain.model.aggregates.User;
import pe.edu.upc.center.booksphere.users.domain.model.queries.GetAllProfilesQuery;
import pe.edu.upc.center.booksphere.users.domain.model.queries.GetProfileByFullNameQuery;
import pe.edu.upc.center.booksphere.users.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handleUserQuery(GetAllProfilesQuery query);
    Optional<User> handleUserQuery(GetProfileByIdQuery query);
    Optional<User> handleUserQuery(GetProfileByFullNameQuery query);

    Optional<?> handle(GetProfileByIdQuery getProfileByIdQuery);

    Optional<?> handle(GetAllProfilesQuery getAllProfilesQuery);
}