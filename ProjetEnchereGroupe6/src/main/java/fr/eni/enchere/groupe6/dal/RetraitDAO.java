package fr.eni.enchere.groupe6.dal;

import java.util.List;

public interface RetraitDAO {
	List<Retrait> findAll();
	
	Retrait findById(Integer id);

	void save(Retrait retrait);
}
