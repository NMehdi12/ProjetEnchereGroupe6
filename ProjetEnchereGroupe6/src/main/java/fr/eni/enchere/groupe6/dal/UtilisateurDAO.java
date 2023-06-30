package fr.eni.enchere.groupe6.dal;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Utilisateur;
import fr.eni.enchere.groupe6.dal.UtilisateurDAOImpl.UtilisateurRowMapper;

public interface UtilisateurDAO {
	List<Utilisateur> findAll();
	Utilisateur findById(Integer id);
	void save(Utilisateur utilisateur);
	void delete(Integer noUtilisateur);
	Utilisateur findByPseudo(String pseudo);
}
