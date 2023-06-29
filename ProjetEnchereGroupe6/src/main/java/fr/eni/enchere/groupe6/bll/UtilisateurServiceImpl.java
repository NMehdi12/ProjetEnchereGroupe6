package fr.eni.enchere.groupe6.bll;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.enchere.groupe6.bo.Utilisateur;
import fr.eni.enchere.groupe6.dal.UtilisateurDAO;
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	UtilisateurDAO utilisateurDAO;
	PasswordEncoder passwordEncoder;
	
	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO,PasswordEncoder passwordEncoder) {
		this.utilisateurDAO = utilisateurDAO;
		this.passwordEncoder=passwordEncoder;
	}

	@Override
	public Utilisateur afficherParNoUtilisateur(Integer noUtilisateur) {
		
		return utilisateurDAO.findById(noUtilisateur);
	}

	@Override
	public void enregistrerUtilisateur(Utilisateur utilisateur) {
		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
		System.out.println(utilisateur.getMotDePasse());
		utilisateurDAO.save(utilisateur);
		
	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.delete(utilisateur.getNoUtilisateur());
		
	}

}
