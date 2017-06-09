package it.uniroma3.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.model.Author;
import it.uniroma3.service.AuthorService;
import it.uniroma3.service.PictureService;

@Controller
public class AuthorController {
@Autowired
private AuthorService authorService;

@GetMapping("/author")
public String showForm(Author author){
	return "form";
}

@PostMapping("/author")
public String checkPictureInfo(@Valid @ModelAttribute Author author,BindingResult bindingResult,Model model){
	if(bindingResult.hasErrors()) {
		return "form";
	} else {
		model.addAttribute(author);
		authorService.add(author);
	}
	return "author";
}
}
