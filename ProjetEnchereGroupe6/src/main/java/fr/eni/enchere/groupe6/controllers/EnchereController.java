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
import fr.eni.enchere.groupe6.bll.EnchereService;
import fr.eni.enchere.groupe6.bll.UtilisateurServiceImpl;
import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Categorie;
import fr.eni.enchere.groupe6.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping
public class EnchereController {

	private ArticleVenduService articleVenduService;
	@Autowired
	private UtilisateurServiceImpl utilisateurService;

	private CategorieService categorieService;
	private EnchereService enchereService;

	public EnchereController(ArticleVenduService articleVenduService, CategorieService categorieService, EnchereService enchereService) {
        super();
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.enchereService = enchereService;
    }

	@GetMapping({ "/", "/encheres" })
	public String afficherListeEnchere(@ModelAttribute("categorie") Categorie categorie, Model model) {
		List<ArticleVendu> articlesVendus = articleVenduService.afficherArticlesVendus();
		List<Categorie> categories = categorieService.afficherListeCategorie();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String utilisateurConnecte = authentication.getName();
		model.addAttribute("utilisateurConnecte", utilisateurConnecte);
		model.addAttribute("articlesVendus", articlesVendus);
		model.addAttribute("categories", categories);
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
	public String enregistrerCompte(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
			BindingResult validationResult) {
		if (validationResult.hasErrors()) {
			return "PageCreerCompte";
		}
		else if (!utilisateur.getMotDePasse().equals(utilisateur.getMotDePasseConfirm())) {
			System.out.println("inscription utilisateur condition");
			return "PageCreerCompte";
		} 
		else {
			utilisateurService.enregistrerUtilisateur(utilisateur);
			System.out.println("inscription utilisateur");
			return "redirect:/encheres";
		}
	}

	@GetMapping("/rechercher")
	public String rechercherParNom(@RequestParam("nomArticle") String nomArticle, Categorie categorie, Model modele) {
		List<ArticleVendu> articleVendus = articleVenduService.afficherResultatRecherche(nomArticle);
		List<Categorie> categories = categorieService.afficherListeCategorie();
		modele.addAttribute("articleVendu", articleVendus);
		modele.addAttribute("categories", categories);
		System.out.println("je passe par le controller de recherche");
		return "PageAccueilNonConnecte";
	}

	@GetMapping("/filtreCategorie")
	public String rechercheParCategorie(@RequestParam Integer noCategorie, Categorie categorie, Model modele) {
		List<ArticleVendu> articleVendus;
		articleVendus = articleVenduService.afficherResultatParCategorie(categorie);

		List<Categorie> categories = categorieService.afficherListeCategorie();

		modele.addAttribute("articleVendu", articleVendus);
		modele.addAttribute("categories", categories);
		modele.addAttribute("noCategorie", categorie.getNoCategorie());
		System.out.println("passe par le controller filtrecategorie");
		return "PageAccueilNonConnecte";

	}
	
	@GetMapping("/FiltreMesVentesEnCours")
    public String afficherListeEnchereRadio(Authentication authentication,Utilisateur utilisateur,Model model) {

        String username = authentication.getName(); // Obtenez le nom d'utilisateur de l'authentification
        Integer noUtilisateurConnecte = utilisateurService.afficherNoUtilisateurViaPseudo(username);
        utilisateur.setNoUtilisateur(noUtilisateurConnecte);

        List<ArticleVendu> articlesVendus = articleVenduService.afficherResultatParNoUtilisateur(utilisateur);
        model.addAttribute("noUtilisateur",noUtilisateurConnecte );
        model.addAttribute("articleVendu", articlesVendus);
        System.out.println(noUtilisateurConnecte);
       
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
	public String vueMonProfil(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String noUser = authentication.getName();
		utilisateur = utilisateurService.afficherParPseudo(noUser);
		System.out.println("Valeur de noUser de mon profil : " + noUser);
		model.addAttribute("utilisateur", utilisateur);
		return "PageMonProfil";
	}

	@GetMapping("/modifierMonProfil")
	public String vueModifierProfil(@ModelAttribute("utilisateur") Utilisateur utilisateur,
			@RequestParam("noUtilisateur") Integer noUtilisateur, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String noUser = authentication.getName();
		utilisateur = utilisateurService.afficherParPseudo(noUser);
		noUtilisateur = utilisateur.getNoUtilisateur();
		System.out.println("utilisateur afficher par pseudo par noUser de l'authentication + " + utilisateur);
		model.addAttribute("utilisateur", utilisateur);
		return "PageModifierProfil";
	}

	@PostMapping("/modifierMonProfil")
	public String modifierProfil(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String noUser = authentication.getName();
		Integer noUtilisateur = utilisateurService.afficherNoUtilisateurViaPseudo(noUser);
		utilisateur.setNoUtilisateur(noUtilisateur);
		// System.out.println("Utilisateur connecté : " + utilisateur);
		utilisateurService.majUtilisateur(utilisateur);
		// System.out.println("Utilisateur mis à jour : " + noUser);
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
		enchereService.enregistrerEnchere(articleVendu, authentication);
		return "redirect:/encheresConnecte";
	}

	@PostMapping("/modifierVente")
	public String modificationVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu) {
		articleVenduService.mettreAJourArticle(articleVendu);
		
		
		return "redirect:/encheresMesVentes";
	}

	@GetMapping("/modifierVente")
	public String modifierVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu, Categorie categorie,
			@RequestParam("noArticleVendu") Integer noArticleVendu, Model model) {
		articleVendu = articleVenduService.afficherDetailParNoArticle(noArticleVendu);
		List<Categorie> categories = categorieService.afficherListeCategorie();
		System.out.println(articleVendu.getCategorie().getNoCategorie());

		model.addAttribute("articleVendu", articleVendu);
		model.addAttribute("categories", categories);

		return "PageEnchereNonCommencee";
	}

	// Enregistrement d'un nouvel article en base de données
	@PostMapping("/encheresMesVentes")
	public String enregistrerVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu,
			Authentication authentication) {

		articleVenduService.enregistrerArticle(articleVendu, authentication);

		return "redirect:/encheresMesVentes";

	}

	@GetMapping("/encherir")
	public String vueEncherir(@ModelAttribute("articleVendu") ArticleVendu articleVendu,
			@RequestParam("noArticleVendu") Integer noArticleVendu, Model model) {
		articleVendu = articleVenduService.afficherDetailParNoArticle(noArticleVendu);
		model.addAttribute(articleVendu);
		return "PageEncherir";
	}
	
	@PostMapping("/encherir")
	public String actionEncherir(@RequestParam("nouvelleProposition") Integer nouvelleProposition, ArticleVendu articleVendu, Authentication authentication) {
		System.out.println("[Controller] Montant de la nouvelle proposition : " + nouvelleProposition);
		enchereService.encherir(articleVendu, authentication, nouvelleProposition);
		return "redirect:/";
	}

}
