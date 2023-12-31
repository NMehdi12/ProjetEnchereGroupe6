package fr.eni.enchere.groupe6.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.groupe6.bo.Categorie;
import fr.eni.enchere.groupe6.dal.CategorieDAO;

@Service("categorieService")
public class CategorieServiceImpl implements CategorieService {

	CategorieDAO categorieDAO;
	
	// Constructeur
	public CategorieServiceImpl(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}



	@Override
	public Categorie afficherParNoCategorie(Integer noCategorie) {
		
		return categorieDAO.findById(noCategorie);
	}



	@Override
	public List<Categorie> afficherListeCategorie() {
		
		System.out.println("Je passe par la méthode afficherListeCategorie() de CategorieServiceImpl");
		return categorieDAO.findAll();
	}

}
