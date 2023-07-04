package fr.eni.enchere.groupe6.dal;

import java.util.List;

import org.springframework.security.core.Authentication;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Categorie;


public interface ArticleVenduDAO {
	List<ArticleVendu> findAll();
	
	ArticleVendu findById(Integer id);

	void save(ArticleVendu articleVendu, Authentication authentication);
	
	void delete(ArticleVendu articleVendu);
	
	void update(ArticleVendu articleVendu);

	List<ArticleVendu> findByNom(String nomArticle);
	
	List<ArticleVendu> findByCategorie(Categorie categorie);
	
	
}
