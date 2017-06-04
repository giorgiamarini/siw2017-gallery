package it.uniroma3.validator;

import javax.servlet.http.HttpServletRequest;
import it.uniroma3.model.Picture;

public class PictureValidator {
	
	public PictureValidator(){
		
	}
	
	public boolean validate(HttpServletRequest request){
		boolean isOk = true; 
		String title = request.getParameter("title"); 
		String year = request.getParameter("year"); 
		String technique = request.getParameter("technique");
		String height = request.getParameter("height"); 
		String lenght = request.getParameter("lenght");
		Picture picture = (Picture) request.getAttribute("picture");
		
		if (title==null || title== ""){
			request.setAttribute("errTitle", "Campo obbligatorio");
			isOk = false; 
		} else picture.setTitle(title);
		
		if (technique==null || technique ==""){
			request.setAttribute("errTechnique", "Campo obbligatorio");
			isOk=false; 
		} else picture.setTechnique(technique);
		
		if (height ==null || height ==""){
			request.setAttribute("errHeight", "Campo obbligatorio");
			isOk=false; 
		} else {
			try {
				picture.setHeight(Integer.parseInt(height));
			}
			catch (NumberFormatException e) {
				request.setAttribute("errHeight", "Deve essere un numero!");
				isOk = false;
			}
		}
		
		if (lenght ==null || lenght ==""){
			request.setAttribute("errLenght", "Campo obbligatorio");
			isOk=false; 
		} else {
			try {
				picture.setHeight(Integer.parseInt(height));
			}
			catch (NumberFormatException e) {
				request.setAttribute("errLenght", "Deve essere un numero!");
				isOk = false;
			}
		}
		
		if(year==null|| year==""){
			request.setAttribute("year", year);
			isOk=false; 
		}
		
		return isOk; 
	}
}
