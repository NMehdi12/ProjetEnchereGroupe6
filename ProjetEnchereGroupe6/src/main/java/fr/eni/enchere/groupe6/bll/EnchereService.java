package fr.eni.enchere.groupe6.bll;

import java.security.Timestamp;

import org.springframework.security.core.Authentication;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Enchere;

public interface EnchereService {

	public void enregistrerEnchere(ArticleVendu articleVendu, Enchere enchere);
	
	public Enchere afficherMeilleureEnchereParNoArticle(Integer noArticle);
	
}
