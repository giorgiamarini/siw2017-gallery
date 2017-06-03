package it.unroma3.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import it.uniroma3.model.Author;

public class AuthorValidator {

	public AuthorValidator(){
	}
	
	public boolean validate(HttpServletRequest request){
		boolean isOk = true; 
		
		String name = request.getParameter("name"); 
		String surname = request.getParameter("surname"); 
		String nationality = request.getParameter("nationality");
		String birthDate = request.getParameter("birthDate"); 
		String deathDate = request.getParameter("deathDate"); 
		Author author = (Author) request.getAttribute("author"); 
		
		if (name==null||name==""){
			request.setAttribute("errAuthorName", "Campo obbligatorio"); 
			isOk = false; 
		} else author.setName(name);
		
		if (surname == null || surname == ""){
			request.setAttribute("errAuthorSurname", "Campo obbligatorio");
			isOk = false; 
		} else author.setSurname(surname);
		
		if (nationality == null || nationality == "" ){
			request.setAttribute("errAuthorNationality", "Campo obbligatorio");
			isOk = false; 
		} else author.setNationality(nationality);
		
		if(birthDate== null || birthDate.equals("")) {
			request.setAttribute("errAuthorBirthDate", "Campo obbligatorio");
			isOk = false;
		} else {
			try {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				author.setBirthDate(df.parse(birthDate));
				} 
			catch (ParseException e) {
				request.setAttribute("errBirthDate", "Deve essere una data valida!");
				isOk = false;
			}
		}
	
		if(deathDate== null || deathDate.equals("")) {
			request.setAttribute("errAuthorDeathDate", "Campo obbligatorio");
				isOk = false;
		} else {
			try {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				author.setDeathDate(df.parse(birthDate));
				} 
			catch (ParseException e) {
				request.setAttribute("errDeathDate", "Deve essere una data valida!");
				isOk = false;
			}
		}
		
		return isOk; 
	}
}

