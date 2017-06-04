package it.uniroma3.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User {

	// si definiscono i due ruoli gestiti dall'applicazione
	public enum Role {
		ROLE_USER, ROLE_ADMIN
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	private String username;
	private String password;
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCreazione;

	@Enumerated(EnumType.STRING)
	private Role role;

}
