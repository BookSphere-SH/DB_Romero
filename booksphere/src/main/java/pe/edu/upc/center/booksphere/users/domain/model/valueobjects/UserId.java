package pe.edu.upc.center.booksphere.users.domain.model.valueobjects;
public record UserId(Long id) {
    public UserId {
        if (id < 0) {
            throw new IllegalArgumentException("User profileId cannot be negative");
        }
    }

    public UserId() {
        this(0L);
    }
}