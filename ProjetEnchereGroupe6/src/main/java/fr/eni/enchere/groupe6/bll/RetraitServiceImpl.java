package fr.eni.enchere.groupe6.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.enchere.groupe6.dal.RetraitDAO;

@Service
public class RetraitServiceImpl implements RetraitService{

	@Autowired
	RetraitDAO retraitDAO;
	
	@Override
	public void enregistrerRetrait() {
		// TODO Auto-generated method stub
		retraitDAO.save(null);
	}

}
