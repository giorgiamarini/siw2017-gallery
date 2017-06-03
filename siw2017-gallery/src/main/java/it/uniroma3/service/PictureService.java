package it.uniroma3.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.uniroma3.model.Picture;
import it.uniroma3.repository.PictureRepository;

public class PictureService {
	
	private EntityManager em;
	
	public PictureService() {
		
	}
	
 public Picture addPicture(Picture picture){
	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("esercitazione-unit");
		this.em =emf.createEntityManager(); 
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(picture);
		tx.commit();
	    em.close();
	    emf.close();
	    return picture;
 }
public List<Picture> getPictures() {
	EntityManagerFactory emf =Persistence.createEntityManagerFactory("esercitazione-unit");
	this.em = emf.createEntityManager();
	EntityTransaction tx =em.getTransaction();
	tx.begin();
	TypedQuery<Picture> query = em.createNamedQuery("findAll", Picture.class);
	List<Picture> pictures = query.getResultList();
	tx.commit();
	em.close();
	emf.close();
	return pictures; 
}

public Picture getOneProduct(Long id){
	EntityManagerFactory emf =Persistence.createEntityManagerFactory("esercitazione-unit");
	this.em = emf.createEntityManager();
	EntityTransaction tx =em.getTransaction();
	tx.begin();
	Picture picture = em.find(Picture.class,id);
	tx.commit();
	em.close();
	emf.close();
	return picture;
}

public void delete(Picture p) {
	EntityManagerFactory emf= Persistence.createEntityManagerFactory("esercitazione-unit");
	this.em=emf.createEntityManager();
	EntityTransaction et= em.getTransaction();
	et.begin();
	em.remove(em.contains(p)? p: em.merge(p));
	et.commit();
	em.close();
	emf.close();
}
}