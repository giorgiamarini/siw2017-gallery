package it.uniroma3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@NamedQuery(name = "findAll", query = "SELECT p FROM Picture p")
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	@NotNull
	@Size(min=1)
	private String title;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy")
	private Integer year; 
	
	@NotNull
	@Size(min=1)
	private String technique; 
	
	@NotNull
	private Integer lenght; 
	@NotNull
	private Integer height; 
	
	@ManyToOne
	@NotNull
	private Author author;
	
	public Picture(){ }
	
	public Picture(String title, Integer year, String technique, Integer lenght, Integer height, Author author){
		this.title = title; 
		this.year = year; 
		this.technique = technique; 
		this.lenght = lenght; 
		this.height = height; 
		this.author = author; 
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTechnique() {
		return this.technique;
	}

	public void setTechnique(String technique) {
		this.technique = technique;
	}


	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getLenght() {
		return lenght;
	}

	public void setLenght(Integer lenght) {
		this.lenght = lenght;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	} 
	
	
	

}
