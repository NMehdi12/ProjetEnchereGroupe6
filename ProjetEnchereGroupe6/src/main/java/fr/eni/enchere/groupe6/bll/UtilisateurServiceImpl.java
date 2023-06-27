package fr.eni.enchere.groupe6.bll;

import fr.eni.enchere.groupe6.bo.Utilisateur;
import fr.eni.enchere.groupe6.dal.UtilisateurDAO;

public class UtilisateurServiceImpl implements UtilisateurService {

	UtilisateurDAO utilisateurDAO;
	
	@Override
	public Utilisateur afficherParNoUtilisateur(Integer noUtilisateur) {
		
		return utilisateurDAO.findByNoUtilisateur(noUtilisateur);
	}

	@Override
	public void enregistrerUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.save(utilisateur);
		
	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.delete(utilisateur);
		
	}

}
