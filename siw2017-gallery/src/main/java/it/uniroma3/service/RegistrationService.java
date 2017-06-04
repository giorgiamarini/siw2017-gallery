package it.uniroma3.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import it.uniroma3.controller.RegistrationController;
import it.uniroma3.domain.User;
import it.uniroma3.repository.UserRepository;

@Service
public class RegistrationService {
	
	private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	UserRepository utenteRepository;
	
	@Transactional
	public void registraUtente(User utente) {	
		log.info("RegistrazioneService: registraUtente");
		utenteRepository.save(utente);
	}
	
/*	public boolean usernamePresente(String username) {
		List<User> listaUtenti = utenteRepository.findByUsername(username);
		return listaUtenti.size() > 0 ? true : false;
	}
	
	public boolean emailPresente(String email) {
		List<User> listaUtenti = utenteRepository.findByEmail(email);
		return listaUtenti.size() > 0 ? true : false;
	}
	 */
	@Transactional
	public List<User> listaUtenti() {
		 List<User> listaUtenti =  Lists.newArrayList(utenteRepository.findAll());
		 return listaUtenti;
	}

}
