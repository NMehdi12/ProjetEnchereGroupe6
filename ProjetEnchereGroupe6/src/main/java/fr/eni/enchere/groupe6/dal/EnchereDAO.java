package fr.eni.enchere.groupe6.dal;

import java.util.List;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Enchere;

public interface EnchereDAO {
	List<Enchere> findAll();
	
	Enchere findById(Integer id);

	void save(Enchere enchere);
	
	void delete(Enchere enchere);
}
