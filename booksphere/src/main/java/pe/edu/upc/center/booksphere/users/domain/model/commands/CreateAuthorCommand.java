package pe.edu.upc.center.booksphere.users.domain.model.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateAuthorCommand(
        @NotBlank String fullName,
        @NotNull LocalDate birthday,
        @NotBlank String gender,
        @NotBlank String favoriteBook,
        @NotNull LocalDate registerDate,
        @Email @NotBlank String email,
        String description,
        @NotBlank String publisher,
        List<String> books
) {
}