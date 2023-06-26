package fr.eni.enchere.groupe6.dal;

import java.util.List;

public interface UtilisateurDAO {
	List<Utilisateur> findAll();
	
	Utilisateur findById(Integer id);

	void save(Utilisateur utilisateur);
}
