package pe.edu.upc.center.booksphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BooksphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksphereApplication.class, args);
	}

}
