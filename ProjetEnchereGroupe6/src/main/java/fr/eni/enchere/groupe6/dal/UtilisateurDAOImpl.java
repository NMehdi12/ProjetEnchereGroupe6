package fr.eni.enchere.groupe6.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.groupe6.bo.Utilisateur;
@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

	private final static String SELECT_ALL = "select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur from UTILISATEURS";
	private static final String FIND_BY_NO_USER = "select * from utilisateur where no_utilisateur=: noUtilisateur";
	private static final String INSERT = "insert into utilisateurs (no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
			+ "values (:noUtilisateur, :pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, :credit, :administrateur)";
	private final static String DELETE = "delete  UTILISATEURS where no_utilisateur= :noUtilisateur";

	private NamedParameterJdbcTemplate njt;

	class UtilisateurRowMapper implements RowMapper<Utilisateur> {

		@Override
		public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setPrenom(rs.getString("prenom"));
			utilisateur.setEmail(rs.getString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCodePostal(rs.getString("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
			utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
			utilisateur.setCredit(rs.getInt("credit"));
			utilisateur.setAdministrateur(rs.getBoolean("administrateur"));

			return utilisateur;
		}

	}

	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> utilisateurs = njt.query(SELECT_ALL, new UtilisateurRowMapper());
		System.out.println("Liste des utilisateurs :  " + utilisateurs);
		return utilisateurs;

	}

	@Override
	public Utilisateur findByNoUtilisateur(Integer noUtilisateur) {

		MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_utilisateur", noUtilisateur);
		Utilisateur utilisateur = njt.queryForObject(FIND_BY_NO_USER, paramSrc, new UtilisateurRowMapper());
		System.out.println("passe par findByNoUtilisateur");
		return utilisateur;

	}

	@Override
	public void save(Utilisateur utilisateur) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_utilisateur", utilisateur.getNoUtilisateur());
		paramSrc.addValue("pseudo", utilisateur.getPseudo());
		paramSrc.addValue("nom", utilisateur.getNom());
		paramSrc.addValue("prenom", utilisateur.getPrenom());
		paramSrc.addValue("nom", utilisateur.getNom());
		paramSrc.addValue("email", utilisateur.getEmail());
		paramSrc.addValue("nom", utilisateur.getNom());
		paramSrc.addValue("telephone", utilisateur.getTelephone());
		paramSrc.addValue("rue", utilisateur.getRue());
		paramSrc.addValue("code_postal", utilisateur.getCodePostal());
		paramSrc.addValue("ville", utilisateur.getVille());
		paramSrc.addValue("mot_de_passe", utilisateur.getMotDePasse());
		paramSrc.addValue("credit", utilisateur.getCredit());
		paramSrc.addValue("administrateur", utilisateur.isAdministrateur());

		njt.update(INSERT, paramSrc);

	}

	@Override
	public void delete(Integer noUtilisateur) {
		Utilisateur utilisateur = new Utilisateur();
		njt.update(DELETE, new BeanPropertySqlParameterSource(utilisateur));
	}

}
