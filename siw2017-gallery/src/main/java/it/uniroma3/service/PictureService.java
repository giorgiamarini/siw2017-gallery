package it.uniroma3.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import it.uniroma3.model.Picture;
import it.uniroma3.repository.PictureRepository;

@Service
public class PictureService {
	
@Autowired
private PictureRepository pictureRepository;

public Iterable<Picture> findAll(){
	return this.pictureRepository.findAll();
}

@Transictional
public void add(final Picture picture) {
	this.pictureRepository.save(picture);
}
	
 
}