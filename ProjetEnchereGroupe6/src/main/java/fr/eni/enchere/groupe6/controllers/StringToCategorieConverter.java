package fr.eni.enchere.groupe6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.enchere.groupe6.bll.CategorieService;
import fr.eni.enchere.groupe6.bo.Categorie;

@Component
public class StringToCategorieConverter implements Converter<String, Categorie> {

	private CategorieService service;
	
	@Autowired
	public void setCategorieService(CategorieService service) {
		this.service = service;
	}
	
	@Override
	public Categorie convert(String source) {
		Integer theId = Integer.parseInt(source);
		System.out.println("Je passe par la conversion de catégorie");
		
		return service.afficherParNoCategorie(theId);
	}

}
