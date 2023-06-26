package fr.eni.enchere.groupe6.dal;

import java.util.List;

public interface EnchereDAO {
	List<Enchere> findAll();
	
	Enchere findById(Integer id);

	void save(Enchere enchere);
}
