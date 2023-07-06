package fr.eni.enchere.groupe6.dal;

import java.util.List;

import org.springframework.security.core.Authentication;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Enchere;

public interface EnchereDAO {
	List<Enchere> findAll();
	
	Enchere findById(Integer id);

	void save(ArticleVendu articleVendu, Authentication authentication);
	
	void update(ArticleVendu articleVendu, Authentication authentication, Integer nouvelleEnchere);
	
	void delete(Enchere enchere);
}
