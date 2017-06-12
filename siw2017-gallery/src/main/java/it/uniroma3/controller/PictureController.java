package it.uniroma3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.model.Picture;
import it.uniroma3.service.PictureService;

@Controller
public class PictureController {
@Autowired
private PictureService pictureService;

@GetMapping("/picture")
public String showForm(Picture picture){
	return "form";
}

@PostMapping("/picture")
public String checkPictureInfo(@Valid @ModelAttribute Picture picture,BindingResult bindingResult,Model model){
	if(bindingResult.hasErrors()) {
		return "form";
	} else {
		model.addAttribute(picture);
		pictureService.add(picture);
	}
	return "picture";
}
}
