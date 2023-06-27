package fr.eni.enchere.groupe6.dal;

import java.util.List;

import fr.eni.enchere.groupe6.bo.ArticleVendu;


public interface ArticleVenduDAO {
List<ArticleVendu> findAll();
	
	ArticleVendu findById(Integer id);

	void save(ArticleVendu articleVendu);
	
	void delete(ArticleVendu articleVendu);
}
