package fr.eni.enchere.groupe6.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping
public class EnchereController {
	@GetMapping ({"/", "/encheres"})
	public String afficherListeEnchere() {
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
	
	@PostMapping ("/inscription")
	public String enregistrerCompte() {
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
	public String modifierVente() {
		return "PageEnchereNonCommencee";
	}
	
	
	@PostMapping ("/encheresMesVentes")
	public String enregistrerVente() {
		return "redirect:/encheresConnecte";
	}
	
	@GetMapping ("/encherir")
	public String vueEncherir() {
		return "PageEncherir";
	}
	
	
}
