package pe.edu.upc.center.booksphere.users.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.center.booksphere.users.domain.model.commands.DeleteAuthorCommand;
import pe.edu.upc.center.booksphere.users.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.center.booksphere.users.domain.model.queries.GetAllProfilesQuery;
import pe.edu.upc.center.booksphere.users.domain.model.queries.GetProfileByIdQuery;
import pe.edu.upc.center.booksphere.users.domain.services.AuthorCommandService;
import pe.edu.upc.center.booksphere.users.domain.services.AuthorQueryService;
import pe.edu.upc.center.booksphere.users.domain.services.UserCommandService;
import pe.edu.upc.center.booksphere.users.domain.services.UserQueryService;
import pe.edu.upc.center.booksphere.users.interfaces.rest.resources.CreateProfileResource;
import pe.edu.upc.center.booksphere.users.interfaces.rest.resources.ProfileResource;
import pe.edu.upc.center.booksphere.users.interfaces.rest.transform.*;
import pe.edu.upc.center.booksphere.users.domain.model.aggregates.User;
import pe.edu.upc.center.booksphere.users.domain.model.aggregates.Author;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfileController {

    private final UserQueryService userQueryService;
    private final AuthorQueryService authorQueryService;
    private final UserCommandService userCommandService;
    private final AuthorCommandService authorCommandService;

    public ProfileController(UserQueryService userQueryService, AuthorQueryService authorQueryService,
                              UserCommandService userCommandService, AuthorCommandService authorCommandService) {
        this.userQueryService = userQueryService;
        this.authorQueryService = authorQueryService;
        this.userCommandService = userCommandService;
        this.authorCommandService = authorCommandService;
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        var createProfileCommand = resource.publisher() == null ?
                CreateUserCommandFromResourceAssembler.toCommandFromResource(resource) :
                CreateAuthorCommandFromResourceAssembler.toCommandFromResource(resource);
        Long profileId = resource.publisher() == null ?
                this.userCommandService.handle(createProfileCommand) :
                this.authorCommandService.handle(createProfileCommand);

        if (profileId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        GetProfileByIdQuery getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        Optional<?> optionalProfile = resource.publisher() == null ?
                this.userQueryService.handle(getProfileByIdQuery) :
                this.authorQueryService.handle(getProfileByIdQuery);

        ProfileResource profileResource = resource.publisher() == null ?
                UserResourceFromEntityAssembler.toResourceFromEntity((User) optionalProfile.get()) :
                AuthorResourceFromEntityAssembler.toResourceFromEntity((Author) optionalProfile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var users = this.userQueryService.handle(getAllProfilesQuery);
        List<ProfileResource> userResources = users != null ? users.stream()
                .map(user -> UserResourceFromEntityAssembler.toResourceFromEntity((User) user))
                .collect(Collectors.toList()) : List.of();

        var authors = this.authorQueryService.handle(getAllProfilesQuery);
        List<ProfileResource> authorResources = authors != null ? authors.stream()
                .map(author -> AuthorResourceFromEntityAssembler.toResourceFromEntity((Author) author))
                .collect(Collectors.toList()) : List.of();

        userResources.addAll(authorResources);
        return ResponseEntity.ok(userResources);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        Optional<?> optionalProfile = this.userQueryService.handle(getProfileByIdQuery);
        if (optionalProfile.isEmpty()) {
            optionalProfile = this.authorQueryService.handle(getProfileByIdQuery);
            if (optionalProfile.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
        }

        ProfileResource profileResource = optionalProfile.get() instanceof User ?
                UserResourceFromEntityAssembler.toResourceFromEntity((User) optionalProfile.get()) :
                AuthorResourceFromEntityAssembler.toResourceFromEntity((Author) optionalProfile.get());
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long profileId, @RequestBody ProfileResource resource) {
        var updateProfileCommand = resource.publisher() == null ?
                UpdateUserCommandFromResourceAssembler.toCommandFromResource(profileId, resource) :
                UpdateAuthorCommandFromResourceAssembler.toCommandFromResource(profileId, resource);
        Long updatedProfileId = resource.publisher() == null ?
                this.userCommandService.handle(updateProfileCommand) :
                this.authorCommandService.handle(updateProfileCommand);

        if (updatedProfileId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        GetProfileByIdQuery getProfileByIdQuery = new GetProfileByIdQuery(updatedProfileId);
        Optional<?> optionalProfile = resource.publisher() == null ?
                this.userQueryService.handle(getProfileByIdQuery) :
                this.authorQueryService.handle(getProfileByIdQuery);

        ProfileResource profileResource = resource.publisher() == null ?
                UserResourceFromEntityAssembler.toResourceFromEntity((User) optionalProfile.get()) :
                AuthorResourceFromEntityAssembler.toResourceFromEntity((Author) optionalProfile.get());
        return ResponseEntity.ok(profileResource);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long profileId) {
        try {
            this.userCommandService.handle(new DeleteUserCommand(profileId));
        } catch (Exception e) {
            try {
                this.authorCommandService.handle(new DeleteAuthorCommand(profileId));
            } catch (Exception ex) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.noContent().build();
    }
}
