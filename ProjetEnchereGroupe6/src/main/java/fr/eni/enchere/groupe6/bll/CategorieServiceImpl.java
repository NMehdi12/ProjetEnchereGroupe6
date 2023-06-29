package fr.eni.enchere.groupe6.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.groupe6.bo.Categorie;

@Service
public class CategorieServiceImpl implements CategorieService {

	CategorieDAO categorieDAO;
	
	// Constructeur
	public CategorieServiceImpl(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}



	@Override
	public Categorie afficherParNoCategorie(Integer noCategorie) {
		categorieDAO.findById(noCategorie);
		return null;
	}



	@Override
	public List<Categorie> afficherListeCategorie() {
		categorieDAO.findAll();
		return null;
	}

}
