package it.uniroma3.repository;

import javax.persistence.EntityManager;
import it.uniroma3.model.Picture;

public class PictureRepository extends CrudRepositoryJPA<Picture> {

	public PictureRepository(EntityManager em) {
		super(em, Picture.class);
	}

}
