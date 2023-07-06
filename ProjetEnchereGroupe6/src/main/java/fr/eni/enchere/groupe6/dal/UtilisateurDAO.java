package fr.eni.enchere.groupe6.dal;

import java.util.List;

import org.springframework.security.core.Authentication;

import fr.eni.enchere.groupe6.bo.Utilisateur;

public interface UtilisateurDAO {
	List<Utilisateur> findAll();
	Utilisateur findById(Integer id);
	void save(Utilisateur utilisateur);
	void delete(Integer noUtilisateur);
	Utilisateur findByPseudo(String pseudo);
	Integer findNoUtilisateurByPseudo(String pseudo);
	void update(Utilisateur utilisateur);
	Utilisateur findByEmail(String email);
	void crediter(Integer nouveauMontant, Integer noArticle);
	void debiter(Integer nouveauMontant, Authentication authentication);
}
