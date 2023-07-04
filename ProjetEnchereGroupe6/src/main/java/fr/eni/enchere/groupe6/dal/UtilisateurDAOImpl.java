package fr.eni.enchere.groupe6.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.groupe6.bo.Utilisateur;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

	private final static String SELECT_ALL = "select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur from UTILISATEURS";
	private static final String FIND_BY_NO_USER = "select * from UTILISATEURS where no_utilisateur=:no_utilisateur";
	private static final String FIND_BY_PSEUDO = "select * from UTILISATEURS where pseudo=:pseudo";
	private static final String INSERT = "insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
            + "values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit, :administrateur)";
		
	private static final String UPDATE = "update UTILISATEURS set pseudo=:pseudo, nom=:nom, prenom=:prenom, email=:email, telephone=:telephone, rue=:rue, code_postal=:code_postal, ville=:ville, mot_de_passe=:mot_de_passe, credit=:credit, administrateur=:administrateur where no";
	private final static String DELETE = "delete  UTILISATEURS where no_utilisateur= :noUtilisateur";
	private final static String FIND_NO_USER_BY_PSEUDO = "select no_utilisateur from UTILISATEURS where pseudo = :pseudo";

	private NamedParameterJdbcTemplate njt;

	public UtilisateurDAOImpl(NamedParameterJdbcTemplate njt) {
		this.njt = njt;
	}

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
	public Utilisateur findById(Integer noUtilisateur) {

		MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_utilisateur", noUtilisateur);
		Utilisateur utilisateur = njt.queryForObject(FIND_BY_NO_USER, paramSrc, new UtilisateurRowMapper());
		System.out.println("passe par findByNoUtilisateur");
		return utilisateur;
	}

	@Override
	public Utilisateur findByPseudo(String pseudo){
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("pseudo", pseudo);
		List<Utilisateur> utilisateurs = njt.query(FIND_BY_PSEUDO, paramSrc, new UtilisateurRowMapper());
	    
	    if (utilisateurs.isEmpty()) {
	        System.out.println("passe par findbyPseudo utilisateur n'existe pas");
	        return null;
	    } else {
	        System.out.println("passe par findbyPseudo l'utilisateur existe");
	        return utilisateurs.get(0);
	    }
	}
		
	@Override
	public void save(Utilisateur utilisateur) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("pseudo", utilisateur.getPseudo());
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
		paramSrc.addValue("credit", 0);
		paramSrc.addValue("administrateur", false);
		njt.update(INSERT, paramSrc);
		System.out.println("passe par le save UtilisateurDAOimpl");
	}
	
	@Override
	public void update(Utilisateur utilisateur) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("pseudo", utilisateur.getPseudo());
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
		paramSrc.addValue("credit", 0);
		paramSrc.addValue("administrateur", false);
		njt.update(UPDATE, paramSrc);
		System.out.println("passe par le update UtilisateurDAOimpl");
		System.out.println(utilisateur);
	}
	

	@Override
	public void delete(Integer noUtilisateur) {
		Utilisateur utilisateur = new Utilisateur();
		njt.update(DELETE, new BeanPropertySqlParameterSource(utilisateur));
	}

	@Override
	public Integer findNoUtilisateurByPseudo(String pseudo) {
		Integer noUtilisateur;
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("pseudo", pseudo);
		
noUtilisateur = njt.queryForObject(FIND_NO_USER_BY_PSEUDO, paramSrc, Integer.class);
		return noUtilisateur;
	}
}
