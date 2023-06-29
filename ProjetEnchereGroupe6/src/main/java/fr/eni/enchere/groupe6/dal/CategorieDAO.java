package fr.eni.enchere.groupe6.dal;

import java.util.List;

import fr.eni.enchere.groupe6.bo.Categorie;
import fr.eni.enchere.groupe6.bo.Retrait;

public interface CategorieDAO {
	List<Categorie> findAll();
	
	Categorie findById (Integer noCategorie);
	
	void save(Categorie categorie);
	
	void delete(Categorie categorie);
}
