package fr.eni.enchere.groupe6.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.groupe6.bo.Categorie;
import fr.eni.enchere.groupe6.dal.CategorieDAO;

@Service
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
		
		return categorieDAO.findAll();
	}

}
