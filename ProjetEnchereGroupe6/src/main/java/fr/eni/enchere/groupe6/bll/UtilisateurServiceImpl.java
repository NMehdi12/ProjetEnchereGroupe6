package fr.eni.enchere.groupe6.bll;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.enchere.groupe6.bo.Utilisateur;
import fr.eni.enchere.groupe6.dal.UtilisateurDAO;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	UtilisateurDAO utilisateurDAO;
	PasswordEncoder passwordEncoder;

	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, PasswordEncoder passwordEncoder) {
		this.utilisateurDAO = utilisateurDAO;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Utilisateur afficherParNoUtilisateur(Integer noUtilisateur) {

		return utilisateurDAO.findById(noUtilisateur);
	}

	@Override
	public void enregistrerUtilisateur(Utilisateur utilisateur) {
		try {
			System.out.println("passe par try");
			if (afficherParMail(utilisateur.getEmail()) != null) {
				System.out.println("Le  mail existe déjà");
			} else if (afficherParPseudo(utilisateur.getPseudo()) != null) {
				System.out.println("Le pseudo existe déjà");
			} else {
				utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
				System.out.println(utilisateur.getMotDePasse());
				utilisateurDAO.save(utilisateur);
				System.out.println("l'utilisateur est enregistré");
			}
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite : " + e.getMessage());
		}

	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.delete(utilisateur.getNoUtilisateur());

	}

	@Override
	public Utilisateur afficherParPseudo(String pseudo) {

		return utilisateurDAO.findByPseudo(pseudo);
	}

	@Override
	public String recupererUtilisateurConnecte() {
		System.out.println("Je passe par recupererUtilisateurConnecte");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String pseudo = authentication.getName();
		return pseudo;
	}

	@Override
	public String recupererEmailUtilisateurConnecte() {
		System.out.println("Je passe par recupererUtilisateurConnecte");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String pseudo = authentication.getName();
		return afficherParNoUtilisateur(afficherNoUtilisateurViaPseudo(pseudo)).getEmail();
	}

	@Override
	public String recupererMdpUtilisateurConnecte() {
		System.out.println("Je passe par recupererUtilisateurConnecte");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String pseudo = authentication.getName();
		return afficherParNoUtilisateur(afficherNoUtilisateurViaPseudo(pseudo)).getMotDePasse();
	}

	@Override
	public void majUtilisateur(Utilisateur utilisateur) {
		try {
			if (!passwordEncoder.matches(utilisateur.getMotDePasse(),recupererMdpUtilisateurConnecte())){
				System.out.println("Le mot de passe actuel est incorrect");
				// lever une exception
			} else if (!utilisateur.getPseudo().equalsIgnoreCase(recupererUtilisateurConnecte())
					&& afficherParPseudo(utilisateur.getPseudo()) != null) {
				System.out.println("Le pseudo existe déjà");
			} else if (!utilisateur.getEmail().equalsIgnoreCase(recupererEmailUtilisateurConnecte())
					&& afficherParMail(utilisateur.getEmail()) != null) {
				System.out.println("L'email existe déjà");
			} else {
				utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasseConfirm()));
				System.out.println(utilisateur.getMotDePasse());
				System.out.println("Je passe par la méthode majUtilisateur de UtilsateurServiceImpl");
				utilisateurDAO.update(utilisateur);
			}
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite : " + e.getMessage());
		}

	}

	@Override
	public Integer afficherNoUtilisateurViaPseudo(String pseudo) {
		System.out.println("Je passe par la méthode afficherNoUtilisateurViaPseudo de UtilsateurServiceImpl");
		return utilisateurDAO.findNoUtilisateurByPseudo(pseudo);
	}

	@Override
	public Utilisateur afficherParMail(String email) {
		return utilisateurDAO.findByEmail(email);
	}

}
