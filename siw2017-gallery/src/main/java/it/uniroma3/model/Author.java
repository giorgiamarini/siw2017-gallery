package it.uniroma3.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name="findAllAuthors", query="SELECT a FROM Author a")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	private String name; 
	private String surname; 
	private String nationality;
	private Date birthDate; 
	private Date deathDate; 
	
	@OneToMany
	private List<Picture> pictures; 
	
	public Author(){ }
	
	public Author(String name, String surname, String nationality, Date birthDate, Date deathDate, List<Picture> pictures){
		this.name = name; 
		this.surname = surname; 
		this.nationality = nationality; 
		this.birthDate = birthDate; 
		this.deathDate = deathDate; 
		this.pictures = pictures; 
	}

	public Long getId(){
		return this.id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	
}
