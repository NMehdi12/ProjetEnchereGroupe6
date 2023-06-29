package fr.eni.enchere.groupe6.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Retrait;

@Repository
public class RetraitDAOImpl implements RetraitDAO{
	
	private static final String INSERT = "insert into RETRAITS (no_article, rue, codePostal, ville) values (:no_article, :rue, :code_postal, :ville)"; 
	private static final String FIND_ID = "select * from RETRAITS where no_article=:no_article"; 
	private static final String FIND_ALL = "select * from RETRAITS";
	private static final String DELETE = "delete from RETRAITS where no_article = :noArticle";
	
	@Autowired
	private NamedParameterJdbcTemplate njt;
	
	@Autowired
	private ArticleVenduDAO articleVenduDAO;
	
	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	// Classe interne
	class RetraitRowMapper implements RowMapper<Retrait> {

		@Override
		public Retrait mapRow(ResultSet rs, int rowNum) throws SQLException {
			Retrait retrait = new Retrait();
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
			
			ArticleVendu articleVendu = null;
			
			try {
				articleVendu = articleVenduDAO.findById(rs.getInt("no_utilisateur"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			retrait.setArticleVendu(articleVendu);
			
			return retrait;
		}
		
	}

	@Override
	public List<Retrait> findAll() {
		List<Retrait> retraits = njt.query(FIND_ALL, new RetraitRowMapper());
		return retraits;
	}

	@Override
	public Retrait findById(Integer id) {
		ArticleVendu articleVendu = new ArticleVendu();
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_article", articleVendu.getNoArticle());
		
		Retrait retrait = njt.queryForObject(FIND_ID, paramSrc, new RetraitRowMapper());
		
		
		return retrait;
	}

	@Override
	public void save(Retrait retrait) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_article", retrait.getArticleVendu().getNoArticle());
		paramSrc.addValue("rue", retrait.getArticleVendu().getUtilisateur().getRue());
		paramSrc.addValue("code_postal", retrait.getArticleVendu().getUtilisateur().getCodePostal());
		paramSrc.addValue("ville", retrait.getArticleVendu().getUtilisateur().getVille());
		
		njt.update(INSERT, paramSrc);
		
		
	}

	@Override
	public void delete(Retrait retrait) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_article", retrait.getArticleVendu().getNomArticle());
		
		njt.update(DELETE, paramSrc);
		
	}

}
