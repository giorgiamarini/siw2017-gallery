package it.uniroma3.repository;

import java.util.List;

import javax.persistence.EntityManager;
import it.uniroma3.domain.User;

public class UserRepository extends CrudRepositoryJPA<User> {

	public UserRepository(EntityManager em) {
		super(em, User.class);
	}

	

}

