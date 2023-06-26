package fr.eni.enchere.groupe6.dal;

import java.util.List;


public interface ArticleVenduDAO {
List<ArticleVendu> findAll();
	
	ArticleVendu findById(Integer id);

	void save(ArticleVendu articleVendu);
}
