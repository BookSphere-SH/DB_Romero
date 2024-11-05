package pe.edu.upc.center.booksphere.users.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record CreateProfileResource(
        String fullName,
        LocalDate birthday,
        String gender,
        String favoriteBook,
        LocalDate registerDate,
        String email,
        String description,
        String publisher,
        List<String> books,
        List<String> library
) {
}