package fr.eni.enchere.groupe6.dal;

import java.util.List;

import org.springframework.security.core.Authentication;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Categorie;
import fr.eni.enchere.groupe6.bo.Utilisateur;


public interface ArticleVenduDAO {
	List<ArticleVendu> findAll();
	
	ArticleVendu findById(Integer id);

	void save(ArticleVendu articleVendu, Authentication authentication);
	
	void delete(ArticleVendu articleVendu);
	
	void update(ArticleVendu articleVendu);
	
	void updatePrixVente(ArticleVendu articleVendu, Integer nouveauPrixVente);

	List<ArticleVendu> findByNom(String nomArticle);
	
	List<ArticleVendu> findByCategorie(Categorie categorie);
	
	List<ArticleVendu> findByNoUtilisateur(Utilisateur utilisateur);
	
	List<ArticleVendu> findByVentesNonCommencees(Utilisateur utilisateur);
	
	List<ArticleVendu> findByVentesTerminees(Utilisateur utilisateur);
	
	List<ArticleVendu> findByMesEncheresEnCours(Utilisateur utilisateur);
	
	List<ArticleVendu> findByMesEncheresTerminees(Utilisateur utilisateur);
}
