package pe.edu.upc.center.booksphere.profiles.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.center.booksphere.profiles.domain.model.commands.CreateAuthorCommand;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotNull
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @NotBlank
    @Column(name = "gender", nullable = false)
    private String gender;

    @NotBlank
    @Column(name = "favorite_book", nullable = false)
    private String favoriteBook;

    @NotNull
    @Column(name = "register_date", nullable = false)
    private LocalDate registerDate;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "publisher", nullable = false)
    private String publisher;

    @ElementCollection
    @CollectionTable(name = "author_books", joinColumns = @JoinColumn(name = "author_id"))
    @Column(name = "book")
    private List<String> books;

    public Author() {
    }

    public Author(CreateAuthorCommand command) {
        this.fullName = command.fullName();
        this.birthday = command.birthday();
        this.gender = command.gender();
        this.favoriteBook = command.favoriteBook();
        this.registerDate = command.registerDate();
        this.email = command.email();
        this.description = command.description();
        this.publisher = command.publisher();
        this.books = command.books();
    }

    public Author updateInformation(String fullName, LocalDate birthday, String gender, String favoriteBook, LocalDate registerDate, String email, String description, String publisher, List<String> books) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.favoriteBook = favoriteBook;
        this.registerDate = registerDate;
        this.email = email;
        this.description = description;
        this.publisher = publisher;
        this.books = books;
        return this;
    }
}
