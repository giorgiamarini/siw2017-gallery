package it.uniroma3.repository;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	
	List<Author> findByName(String name);
	
	List<Author> findBySurname(String surname);

}
