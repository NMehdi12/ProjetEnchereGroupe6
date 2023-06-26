package fr.eni.enchere.groupe6.dal;

import java.util.List;

import fr.eni.enchere.groupe6.bo.Retrait;

public interface RetraitDAO {
	List<Retrait> findAll();
	
	Retrait findById(Integer id);

	void save(Retrait retrait);
}
