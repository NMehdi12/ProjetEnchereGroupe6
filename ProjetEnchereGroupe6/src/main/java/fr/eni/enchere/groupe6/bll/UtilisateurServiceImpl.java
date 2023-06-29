package fr.eni.enchere.groupe6.bll;

import org.springframework.stereotype.Service;

import fr.eni.enchere.groupe6.bo.Utilisateur;
import fr.eni.enchere.groupe6.dal.UtilisateurDAO;
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	UtilisateurDAO utilisateurDAO;
	
	
	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public Utilisateur afficherParNoUtilisateur(Integer noUtilisateur) {
		
		return utilisateurDAO.findById(noUtilisateur);
	}

	@Override
	public void enregistrerUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.save(utilisateur);
		
	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.delete(utilisateur.getNoUtilisateur());
		
	}

}
