package it.uniroma3.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Picture;

public interface PictureRepository extends CrudRepository<Picture, Long> {
	
	List<Picture> findByTitle(String title);
	
	List<Picture> findByAuthor(String author);

}
