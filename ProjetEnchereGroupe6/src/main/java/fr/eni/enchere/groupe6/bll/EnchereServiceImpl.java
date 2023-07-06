package fr.eni.enchere.groupe6.bll;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Enchere;
import fr.eni.enchere.groupe6.dal.EnchereDAO;
import fr.eni.enchere.groupe6.dal.UtilisateurDAO;

@Service
public class EnchereServiceImpl implements EnchereService {
	
	private EnchereDAO enchereDAO;
	private UtilisateurDAO utilisateurDAO;
	
	@Autowired
	private DataSource dataSource;
	
	public EnchereServiceImpl(EnchereDAO enchereDAO, UtilisateurDAO utilisateurDAO) {
		this.enchereDAO = enchereDAO;
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public void enregistrerEnchere(ArticleVendu articleVendu, Authentication authentication) {
		enchereDAO.save(articleVendu, authentication);
		
	}

	@Override
	public Enchere afficherMeilleureEnchereParNoArticle(Integer noArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void encherir(ArticleVendu articleVendu, Authentication authentication, Integer nouvelleEnchere) throws SQLException {
		//enchereDAO.update(articleVendu, authentication, nouvelleEnchere);
		
		Connection connection = null; // Déclaration en dehors du bloc try

		try {
		    connection = dataSource.getConnection();
		    connection.setAutoCommit(false);
		    
		    utilisateurDAO.debiter(nouvelleEnchere, authentication);
		    utilisateurDAO.crediter(nouvelleEnchere, articleVendu.getNoArticle());
		    enchereDAO.update(articleVendu, authentication, nouvelleEnchere);
		    
		    System.out.println("Transaction : Try");
		    
		    connection.commit();
		} catch (SQLException e) {
		    if (connection != null) {
		        connection.rollback();
		        
		        System.out.println("Transaction : Catch");
		    }
		    
		} finally {
		    if (connection != null) {
		        connection.close();
		        System.out.println("Transaction : Finally");
		    }
		}
		
		
		
	}

}
