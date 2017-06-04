package it.uniroma3.repository;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByUsername(String username);
	
	List<User> findByEmail(String email);

}
