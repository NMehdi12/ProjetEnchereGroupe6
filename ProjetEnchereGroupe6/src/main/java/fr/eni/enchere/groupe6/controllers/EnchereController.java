package fr.eni.enchere.groupe6.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Utilisateur;
import jakarta.validation.Valid;



@Controller
@RequestMapping
public class EnchereController {
	@GetMapping ({"/", "/encheres"})
	public String afficherListeEnchere() {
		return "PageAccueilNonConnecte";
	}
	
	@GetMapping ("/logout")
	public String deconnexion() {
		return "PageAccueilNonConnecte";
	}
	
	@GetMapping ("/connexion")
	public String seConnecter() {
		return "PageConnexion";
	}
	
	@GetMapping ("/inscription")
	public String creerCompte() {
		return "PageCreerCompte";
	}
	
	//inscription de l'utilisateur
	@PostMapping ("/inscription")
	public String enregistrerCompte(@Valid @ModelAttribute("utilisateur")Utilisateur utilisateur,BindingResult bindingResult ) {
		
		/////ajouter potentiellement un try catch pour le message d'erreur/////
		
		
		return "redirect:/inscription";
	}
		
	@GetMapping ("/encheresConnecte")
	public String afficherListeEnchereConnecte() {
		return "PageListeEncheresConnecte";
	}
	
	@GetMapping ("/encheresMesVentes")
	public String afficherMesVentes() {
	return "PageListeEnchereMesVentes";
	}
	
	
	@GetMapping ("/profil")
	public String consulterProfil() {
		return "PageProfil";
	}
	@GetMapping ("/monProfil")
	public String vueModifierProfil() {
		return "PageMonProfil";
	}
	
	@PostMapping ("/monProfil")
	public String modifierProfil() {
		return "redirect:/monProfil";
	}
	
	@GetMapping ("/nouvelleVente")
	public String vueAjouterVente() {
		return "PageVendreUnArticle";
	}
	
	@PostMapping ("/nouvelleVente")
	public String ajouterVente() {
		return "redirect:/encheresConnecte";
	}
	
	@GetMapping ("/modifierVente")
	public String modifierVente(@Valid @ModelAttribute ("articleVendu") ArticleVendu articleVendu,BindingResult bindingResult) {
		return "PageEnchereNonCommencee";
	}
	
	
	@PostMapping ("/encheresMesVentes")
	public String enregistrerVente() {
		return "redirect:/encheresConnecte";
	}
	
	
	
}
