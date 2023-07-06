package fr.eni.enchere.groupe6.bll;

import java.security.Timestamp;
import java.sql.SQLException;

import org.springframework.security.core.Authentication;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Enchere;

public interface EnchereService {

	public void enregistrerEnchere(ArticleVendu articleVendu, Authentication authentication);
	
	public Enchere afficherMeilleureEnchereParNoArticle(Integer noArticle);
	
	public void encherir(ArticleVendu articleVendu, Authentication authentication, Integer nouvelleEnchere) throws SQLException;
	
}
