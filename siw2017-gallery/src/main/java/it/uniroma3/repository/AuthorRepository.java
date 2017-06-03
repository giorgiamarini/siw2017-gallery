package it.uniroma3.repository;

import javax.persistence.EntityManager;

import it.uniroma3.model.Author;

public class AuthorRepository extends CrudRepositoryJPA<Author> {

	public AuthorRepository(EntityManager em) {
		super(em, Author.class);
	}
	

}
