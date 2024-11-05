package pe.edu.upc.center.booksphere.users.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.booksphere.users.domain.model.aggregates.User;
import pe.edu.upc.center.booksphere.users.domain.model.queries.GetAllProfilesQuery;
import pe.edu.upc.center.booksphere.users.domain.model.queries.GetProfileByFullNameQuery;
import pe.edu.upc.center.booksphere.users.domain.model.queries.GetProfileByIdQuery;
import pe.edu.upc.center.booksphere.users.domain.services.UserQueryService;
import pe.edu.upc.center.booksphere.users.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handleUserQuery(GetAllProfilesQuery query) {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> handleUserQuery(GetProfileByIdQuery query) {
        return this.userRepository.findById(query.id());
    }

    @Override
    public Optional<User> handleUserQuery(GetProfileByFullNameQuery query) {
        return this.userRepository.findByFullName(query.fullName());
    }

    @Override
    public Optional<?> handle(GetProfileByIdQuery getProfileByIdQuery) {
        return Optional.empty();
    }

    @Override
    public Optional<?> handle(GetAllProfilesQuery getAllProfilesQuery) {
        return Optional.empty();
    }
}