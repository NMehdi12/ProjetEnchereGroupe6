package fr.eni.enchere.groupe6.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.enchere.groupe6.bll.ArticleVenduService;
import fr.eni.enchere.groupe6.bll.CategorieService;
import fr.eni.enchere.groupe6.bll.UtilisateurServiceImpl;
import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Categorie;
import fr.eni.enchere.groupe6.bo.Utilisateur;

@Controller
@RequestMapping
public class EnchereController {

	private ArticleVenduService articleVenduService;
	@Autowired
	private UtilisateurServiceImpl utilisateurService;

	private CategorieService categorieService;

	public EnchereController(ArticleVenduService articleVenduService, CategorieService categorieService) {
		super();
		this.articleVenduService = articleVenduService;
		this.categorieService = categorieService;
	}

	@GetMapping({ "/", "/encheres" })
	public String afficherListeEnchere(Model model) {
		List<ArticleVendu> articlesVendus = articleVenduService.afficherArticlesVendus();
		model.addAttribute("articlesVendus", articlesVendus);
		System.out.println(articlesVendus.toString());
		return "PageAccueilNonConnecte";
	}

//	@PostMapping("/logout") ///// à voir si util
//	public String deconnexion() {
//		return "PageAccueilNonConnecte";
//	}

//	@GetMapping("/login")
//	public String vueSeConnecter() {
//		return "PageConnexion";
//	}

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
//		if(validationResult.hasErrors()) {
//			return "PageCreerCompte";
//		}
//		else if (!utilisateur.getMotDePasse().equals(utilisateur.getMotDePasseConfirm())) {
//			System.out.println("inscription utilisateur condition");
//			return "PageCreerCompte";
//		} else {
		utilisateurService.enregistrerUtilisateur(utilisateur);
		System.out.println("inscription utilisateur");
		return "redirect:/encheres";

	}
	// }
	
	@GetMapping("/rechercher")

    public String rechercherParNom (@RequestParam ("nomArticle") String nomArticle, Model modele){

        List<ArticleVendu> articleVendus = articleVenduService.afficherResultatRecherche(nomArticle);

        modele.addAttribute("articleVendu", articleVendus);

        System.out.println("je passe par le controller de recherche");

        return "PageAccueilNonConnecte";

        

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

	public String consulterProfil(@ModelAttribute("articleVendu") ArticleVendu articleVendu,

			@ModelAttribute("utilisateur") Utilisateur utilisateur, String pseudo, Model model) {

		utilisateur = utilisateurService.afficherParPseudo(pseudo);

		model.addAttribute(utilisateur);

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
	public String vueAjouterVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu,
			@ModelAttribute("utilisateur") Utilisateur utilisateur, Categorie categorie, Model model) {
		List<Categorie> categories = categorieService.afficherListeCategorie();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String noUser = authentication.getName();

		utilisateur = utilisateurService.afficherParPseudo(noUser);

		System.out.println("Valeur de noUser : " + noUser);
		model.addAttribute("categories", categories);
		model.addAttribute("utilisateur", utilisateur);

		return "PageVendreUnArticle";
	}

	@PostMapping("/nouvelleVente")
	public String ajouterVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu,
			Authentication authentication) {

		articleVenduService.enregistrerArticle(articleVendu, authentication);
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
