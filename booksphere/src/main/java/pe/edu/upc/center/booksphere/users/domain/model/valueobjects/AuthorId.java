package pe.edu.upc.center.booksphere.users.domain.model.valueobjects;
public record AuthorId(Long id) {
    public AuthorId {
        if (id < 0) {
            throw new IllegalArgumentException("User profileId cannot be negative");
        }
    }

    public AuthorId() {
        this(0L);
    }
}