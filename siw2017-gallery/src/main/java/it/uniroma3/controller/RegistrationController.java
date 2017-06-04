package it.uniroma3.controller;

	import java.util.Date;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import javax.faces.application.FacesMessage;
	import javax.faces.bean.ManagedBean;
	import javax.faces.bean.ManagedProperty;
	import javax.faces.bean.ViewScoped;
	import javax.faces.context.FacesContext;

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.uniroma3.domain.RegistrationForm;
import it.uniroma3.domain.User;
	import it.uniroma3.service.RegistrationService;

	@ManagedBean(name="registrazioneController")
	@ViewScoped
	public class RegistrationController {
		
		private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

		private RegistrationForm registrazioneForm = new RegistrationForm();
		
		@ManagedProperty(value = "#{registrazioneService}")
	    private RegistrationService registrazioneService;  
		
		
		public String registraUtente() {
			
			log.info("inizio registrazione utente...");
					
			if (paginaValida() == false) {
				return null;
			}

			// si salva nel db il nuovo utente
			User utente = new User();
			utente.setUsername(registrazioneForm.getUsername());
			utente.setPassword(new BCryptPasswordEncoder().encode(registrazioneForm.getPassword()));
			utente.setEmail(registrazioneForm.getEmail());
			utente.setDataOfCreation(new Date());
			
			// di default si assegna all'utente il ruolo ROLE_USER
			utente.setRole(User.Role.ROLE_USER);
			
			registrazioneService.registraUtente(utente);
			
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "L'utente è stato registrato nel sistema");
			context.addMessage(null, message);
			
			cancellaForm();
			
			return null;
		}
		
		public void verificaEsistenzaUsername() {
			log.info("inizio verifica esistenza username...");
			boolean usernamePresente = registrazioneService.usernamePresente(registrazioneForm.getUsername());
			if (usernamePresente) {
				FacesContext.getCurrentInstance().addMessage("registrazione:username", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "L'username scelta è già utilizzata da un altro utente"));
			}
		}
		
		public void verificaRipetizionePassword() {
			log.info("inizio verifica ripetizione password...");
			
			boolean ripetizionePasswordEsatta = registrazioneForm.getPassword().equals(registrazioneForm.getRepassword());
			if (!ripetizionePasswordEsatta) {
				FacesContext.getCurrentInstance().addMessage("registrazione:repassword", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Le password digitate non coincidono"));
			}
		}
		
		public void verificaMail() {
			
			// si verifica che la mail inserita sia valida
			if (!verificaValiditaEmail()) {
				return;
			}
			
			// si verifica che la mail inserita non sia gia' presente nel database
			verificaEsistenzaEmail();
			
		}
		
		public boolean verificaEsistenzaEmail() {
			log.info("verifica esistenza email nel database...");
			boolean emailPresente = registrazioneService.emailPresente(registrazioneForm.getEmail());
			if (emailPresente) {
				FacesContext.getCurrentInstance().addMessage("registrazione:email", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "L'email scelta è già utilizzata da un altro utente"));
			}
			
			return emailPresente;
		}
		
		public boolean verificaValiditaEmail() {
			log.info("verifica validita' campo email...");
			String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
			
			Pattern pattern = Pattern.compile(regex);
			String email = registrazioneForm.getEmail();
			Matcher matcher = pattern.matcher(email);
			
			boolean mailValida = matcher.matches();
			
			if (!mailValida) {
				FacesContext.getCurrentInstance().addMessage("registrazione:email", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "L'email inserita non è valida"));
			}
			
			return mailValida;
		}
		
		
		
		private boolean paginaValida() {
			
			// validazione username
			boolean usernamePresente = registrazioneService.usernamePresente(registrazioneForm.getUsername());
			if (usernamePresente) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "L'username scelta è già utilizzata da un altro utente");
	            context.addMessage("registrazione:username", message);
	            return false;
			}
			
			// validazione password
			if (!registrazioneForm.getPassword().equals(registrazioneForm.getRepassword())) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Le password digitate non coincidono");
	            context.addMessage("registrazione:repassword", message);
	            return false;
			}
			
			// validazione e-mail - il campo email non deve essere vuoto
			if (registrazioneForm.getEmail() == null || registrazioneForm.getEmail().equals("")) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Il campo email non puo' essere vuoto");
	            context.addMessage("registrazione:email", message);
	            return false;
			}
			
			// validazione e-mail - la mail deve essere valida
			if (!verificaValiditaEmail()) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "L'email inserita non è valida");
	            context.addMessage("registrazione:email", message);
				return false;
			}
			
			// validazione e-mail - la mail non deve essere gia' presente nel database
			boolean emailPresente = registrazioneService.emailPresente(registrazioneForm.getEmail());
			if (emailPresente) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "L'email scelta è già utilizzata da un altro utente");
	            context.addMessage("registrazione:email", message);
	            return false;
			}
			
			// la pagina e' valida
			return true;
		}
		
		private void cancellaForm() {
			registrazioneForm.setEmail(null);
			registrazioneForm.setPassword(null);
			registrazioneForm.setUsername(null);
			registrazioneForm.setRepassword(null);
		}

		public RegistrationForm getRegistrazioneForm() {
			return registrazioneForm;
		}

		public void setRegistrationForm(RegistrationForm registrazioneForm) {
			this.registrazioneForm = registrazioneForm;
		}

		public RegistrationService getRegistrazioneService() {
			return registrazioneService;
		}

		public void setRegistrazioneService(RegistrationService registrazioneService) {
			this.registrazioneService = registrazioneService;
		}
		
		
	}

