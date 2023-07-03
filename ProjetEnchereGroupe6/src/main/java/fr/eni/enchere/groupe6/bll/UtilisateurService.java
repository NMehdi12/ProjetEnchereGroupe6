package fr.eni.enchere.groupe6.bll;

import fr.eni.enchere.groupe6.bo.Utilisateur;

public interface UtilisateurService {
	
	public Utilisateur afficherParNoUtilisateur(Integer noUtilisateur);
	
	public void enregistrerUtilisateur(Utilisateur utilisateur);
	
	public void supprimerUtilisateur(Utilisateur utilisateur);
	
	public Utilisateur afficherParPseudo(String pseudo);
	
	public void recupererUtilisateurConnecte();
}
