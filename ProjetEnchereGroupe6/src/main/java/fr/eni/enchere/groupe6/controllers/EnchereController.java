package fr.eni.enchere.groupe6.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.enchere.groupe6.bll.ArticleVenduService;
import fr.eni.enchere.groupe6.bll.UtilisateurServiceImpl;
import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Utilisateur;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class EnchereController {

	private ArticleVenduService articleVenduService;
	@Autowired
	private UtilisateurServiceImpl utilisateurService;

	public EnchereController(ArticleVenduService articleVenduService) {
		super();
		this.articleVenduService = articleVenduService;
	}

	@GetMapping({ "/", "/encheres" })
	public String afficherListeEnchere(Model model) {
		List<ArticleVendu> articlesVendus = articleVenduService.afficherArticlesVendus();
		model.addAttribute("articlesVendus", articlesVendus);
		System.out.println(articlesVendus.toString());
		return "PageAccueilNonConnecte";
	}

	@PostMapping("/logout") ///// à voir si util
	public String deconnexion() {
		return "PageAccueilNonConnecte";
	}

	@GetMapping("/connexion")
	public String vueSeConnecter() {
		return "PageConnexion";
	}

	@PostMapping("/connexion")
	public String seConnecter() {
		return "redirect:/encheresConnecte";
	}

	@GetMapping("/inscription")
	public String creerCompte(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
		return "PageCreerCompte";
	}

	// inscription de l'utilisateur
	@PostMapping("/inscription")
	public String enregistrerCompte(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
		if (!utilisateur.getMotDePasse().equals(utilisateur.getMotDePasseConfirm())) {
			return "PageCreerCompte";
		} else {
			utilisateurService.enregistrerUtilisateur(utilisateur);
			System.out.println("inscription utilisateur");
			return "redirect:/connexion";
			
		}
	}

	@GetMapping("/encheresConnecte")
	public String afficherListeEnchereConnecte() {
		return "PageListeEncheresConnecte";
	}

	@GetMapping("/encheresMesVentes")
	public String afficherMesVentes() {
		return "PageListeEnchereMesVentes";
	}

	@GetMapping("/profil")
	public String consulterProfil() {
		return "PageProfil";
	}

	@GetMapping("/monProfil")
	public String vueModifierProfil() {
		return "PageMonProfil";
	}

	@PostMapping("/monProfil")
	public String modifierProfil() {
		return "redirect:/monProfil";
	}

	@GetMapping("/nouvelleVente")
	public String vueAjouterVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu) {
		return "PageVendreUnArticle";
	}

	@PostMapping("/nouvelleVente")
	public String ajouterVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu) {
		
		return "redirect:/encheresConnecte";
	}

	@GetMapping("/modifierVente")
	public String modifierVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu) {

		return "PageEnchereNonCommencee";
	}

	// Enregistrement d'un nouvel article en base de données
	@PostMapping("/encheresMesVentes")
	public String enregistrerVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu) {
		articleVenduService.enregistrerArticle(articleVendu);
		return "redirect:/encheresMesVentes";
	}

	@GetMapping("/encherir")
	public String vueEncherir() {
		return "PageEncherir";
	}

}
