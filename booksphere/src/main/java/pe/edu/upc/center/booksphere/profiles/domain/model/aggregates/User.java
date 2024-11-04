package pe.edu.upc.center.booksphere.profiles.domain.model.aggregates;

import pe.edu.upc.center.booksphere.profiles.domain.model.commands.CreateUserCommand;
import pe.edu.upc.center.booksphere.profiles.domain.model.commands.UpdateUserCommand;

import java.time.LocalDate;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private LocalDate birthday;
    private String gender;
    private String favoriteBook;
    private LocalDate registerDate;
    private String email;
    private String description;
    private List<String> library;

    public User(CreateUserCommand command) {
        this.name = command.fullName();
        this.birthday = command.birthday();
        this.gender = command.gender();
        this.favoriteBook = command.favoriteBook();
        this.registerDate = command.registerDate();
        this.email = command.email();
        this.description = command.description();
        this.library = command.library();
    }

    public void updateInformation(String name, LocalDate birthday, String gender, String favoriteBook, LocalDate registerDate, String email, String description, List<String> library) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.favoriteBook = favoriteBook;
        this.registerDate = registerDate;
        this.email = email;
        this.description = description;
        this.library = library;
    }

    public Long getId() {
        return id;
    }
}