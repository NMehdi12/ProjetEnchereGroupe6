package fr.eni.enchere.groupe6.bll;

import java.util.List;

import fr.eni.enchere.groupe6.bo.Categorie;

public interface CategorieService {
	
	public Categorie afficherParNoCategorie(Integer noCategorie);
	
	public List<Categorie> afficherListeCategorie();
}
