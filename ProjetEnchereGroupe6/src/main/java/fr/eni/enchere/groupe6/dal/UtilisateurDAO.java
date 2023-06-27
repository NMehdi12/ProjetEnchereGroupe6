package fr.eni.enchere.groupe6.dal;

import java.util.List;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Utilisateur;

public interface UtilisateurDAO {
	List<Utilisateur> findAll();
	Utilisateur findByNoUtilisateur(Integer id);
	void save(Utilisateur utilisateur);
	void delete(Integer noUtilisateur);
}
