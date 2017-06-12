package it.uniroma3.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Author;
import it.uniroma3.repository.AuthorRepository;

@Service
public class AuthorService {
	
@Autowired
private AuthorRepository authorRepository;

public Iterable<Author> findAll(){
	return this.authorRepository.findAll();
}

@Transictional
public void add(final Author author) {
	this.authorRepository.save(author);
}
	
 
}