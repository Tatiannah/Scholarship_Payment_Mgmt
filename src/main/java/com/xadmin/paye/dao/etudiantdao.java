package com.xadmin.paye.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.paye.bean.etudiant;

public class etudiantdao {
	 private static final String JDBC_URL = "jdbc:mysql://localhost:3305/payement";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "admin";
	    private static final String INSERT_ETUDIANT_SQL = "INSERT INTO `etudiant` (`matricule`, `nom`, `sexe`, `datenais`, `institution`, `niveau`, `mail`, `annee_univ`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    private static final String SELECT_ETUDIANT_BY_ID = "SELECT id, matricule, nom, sexe, datenais, institution, niveau, mail, annee_univ FROM etudiant WHERE id =?";
	    private static final String SELECT_ETUDIANT_ALL = "SELECT * FROM etudiant";
	    private static final String DELETE_ETUDIANT_SQL = "DELETE FROM etudiant WHERE id = ?;";
	    private static final String UPDATE_ETUDIANT_SQL = "UPDATE etudiant SET matricule= ?, nom= ?, sexe= ?, datenais= ?, institution= ?, niveau= ?, mail= ?, annee_univ= ? WHERE id = ?;";
       private static final String SEARCH_ETUDIANT_SQL = "SELECT * FROM etudiant WHERE nom LIKE ?";
	 
	    public etudiantdao() {}

	    protected Connection getConnection() {
	        Connection connection = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	            System.out.println("Connection établie avec succès.");
	        } catch (ClassNotFoundException e) {
	            System.err.println("Erreur : Classe non trouvée : " + e.getMessage());
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.err.println("Erreur SQL : " + e.getMessage());
	            e.printStackTrace();
	        }
	        if (connection == null) {
	            System.err.println("Échec de l'établissement de la connexion.");
	        }
	        return connection;
	    }

	    public void insertEtudiant(etudiant etudiant) throws SQLException {
	        System.out.println(INSERT_ETUDIANT_SQL);
	        Connection connection = getConnection();
	        if (connection == null) {
	            System.err.println("La connexion à la base de données n'a pas pu être établie.");
	            return;
	        }
	        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ETUDIANT_SQL)) {
	            preparedStatement.setString(1, etudiant.getMatricule());
	            preparedStatement.setString(2, etudiant.getNom());
	            preparedStatement.setString(3, etudiant.getSexe());
	            preparedStatement.setDate(4, new Date(etudiant.getDatenais().getTime()));
	            preparedStatement.setString(5, etudiant.getInstitution());
	            preparedStatement.setString(6, etudiant.getNiveau());
	            preparedStatement.setString(7, etudiant.getMail());
	            preparedStatement.setString(8, etudiant.getAnnee_univ());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e : ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }

	    // SELECT
	    public etudiant selectEtudiant(int id) {
	        etudiant Etudiant = null;
	        Connection connection = getConnection();
	        if (connection == null) {
	            System.err.println("La connexion à la base de données n'a pas pu être établie.");
	            return null;
	        }
	        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ETUDIANT_BY_ID)) {
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                String matricule = rs.getString("matricule");
	                String nom = rs.getString("nom");
	                String sexe = rs.getString("sexe");
	                Date datenais = rs.getDate("datenais");
	                String institution = rs.getString("institution");
	                String niveau = rs.getString("niveau");
	                String mail = rs.getString("mail");
	                String annee_univ = rs.getString("annee_univ");
	                Etudiant = new etudiant(id, matricule, nom, sexe, datenais, institution, niveau, mail, annee_univ);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return Etudiant;
	    }

	    // SELECT ALL
	    public List<etudiant> selectAllEtudiant() {
	        List<etudiant> Etudiant = new ArrayList<>();
	        Connection connection = getConnection();
	        if (connection == null) {
	            System.err.println("La connexion à la base de données n'a pas pu être établie.");
	            return Etudiant;
	        }
	        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ETUDIANT_ALL)) {
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String matricule = rs.getString("matricule");
	                String nom = rs.getString("nom");
	                String sexe = rs.getString("sexe");
	                Date datenais = rs.getDate("datenais");
	                String institution = rs.getString("institution");
	                String niveau = rs.getString("niveau");
	                String mail = rs.getString("mail");
	                String annee_univ = rs.getString("annee_univ");
	                Etudiant.add(new etudiant(id, matricule, nom, sexe, datenais, institution, niveau, mail, annee_univ));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return Etudiant;
	    }
//RECHERCHE
	    public List<etudiant> searchEtudiant(String query) {
	    	List<etudiant> Etudiant = new ArrayList<>();
	    	
	    	Connection connection = getConnection();
	    	  if (connection == null) {
		            System.err.println("La connexion à la base de données n'a pas pu être établie.");
		            return Etudiant;
		        }
	    			try(PreparedStatement statement = connection.prepareStatement(SEARCH_ETUDIANT_SQL)){
	    		statement.setString(1, "%" + query + "%");
	    		try(ResultSet rs = statement.executeQuery()) {
	    			while (rs.next()) {
	    				   int id = rs.getInt("id");
	   	                String matricule = rs.getString("matricule");
	   	                String nom = rs.getString("nom");
	   	                String sexe = rs.getString("sexe");
	   	                Date datenais = rs.getDate("datenais");
	   	                String institution = rs.getString("institution");
	   	                String niveau = rs.getString("niveau");
	   	                String mail = rs.getString("mail");
	   	                String annee_univ = rs.getString("annee_univ");
	 	                etudiant Etudiants = new etudiant(id, matricule, nom, sexe, datenais, institution, niveau, mail, annee_univ);
	 	                Etudiant.add(Etudiants);
	 	               
	    			}
	    		}
	    	} catch (SQLException ex) {
	    		ex.printStackTrace();
	    	}
	    	return Etudiant;
	    }
	    // UPDATE
	    public boolean updateEtudiant(etudiant Etudiant) throws SQLException {
	        boolean rowUpdated;
	        Connection connection = getConnection();
	        if (connection == null) {
	            System.err.println("La connexion à la base de données n'a pas pu être établie.");
	            return false;
	        }
	        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ETUDIANT_SQL)) {
	            System.out.println("updated etudiant :" + statement);
	            statement.setString(1, Etudiant.getMatricule());
	            statement.setString(2, Etudiant.getNom());
	            statement.setString(3, Etudiant.getSexe());
	            statement.setDate(4, new Date(Etudiant.getDatenais().getTime()));
	            statement.setString(5, Etudiant.getInstitution());
	            statement.setString(6, Etudiant.getNiveau());
	            statement.setString(7, Etudiant.getMail());
	            statement.setString(8, Etudiant.getAnnee_univ());
	            statement.setInt(9, Etudiant.getId());
	            rowUpdated = statement.executeUpdate() > 0;
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return rowUpdated;
	    }

	    // DELETE
	    public boolean deleteEtudiant(int id) throws SQLException {
	        boolean rowDeleted;
	        Connection connection = getConnection();
	        if (connection == null) {
	            System.err.println("La connexion à la base de données n'a pas pu être établie.");
	            return false;
	        }
	        try (PreparedStatement statement = connection.prepareStatement(DELETE_ETUDIANT_SQL)) {
	            statement.setInt(1, id);
	            rowDeleted = statement.executeUpdate() > 0;
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return rowDeleted;
	    }
	    
	    
	//filtrage 
	    public List<String> getAllNiveaux() throws SQLException {
	    	List<String> niveaux = new ArrayList<>();
	    	String sql ="SELECT DISTINCT niveau FROM etudiant";
	    	try (Connection connection = getConnection();
	    			PreparedStatement statement = connection.prepareStatement(sql);
	    			ResultSet rs = statement.executeQuery()){
	    		while (rs.next()) {
	    			niveaux.add(rs.getString("niveau"));
	    		}
	    	}
	    	return niveaux;
	    }
	    public List<String> getAllInstitutions() throws SQLException {
	    	List<String> institutions = new ArrayList<>();
	    	String sql ="SELECT DISTINCT institution FROM etudiant";
	    	try (Connection connection = getConnection();
	    			PreparedStatement statement = connection.prepareStatement(sql);
	    			ResultSet rs = statement.executeQuery()){
	    		while (rs.next()) {
	    			institutions.add(rs.getString("institution"));
	    		}
	    	}
	    	return institutions;
	    }
	    
	    public List<etudiant> FilterEtudiants(String niveau, String institution) throws SQLException {
	    	List<etudiant> Etudiant = new ArrayList<>();
	    	
	    	StringBuilder sql = new StringBuilder("SELECT * FROM etudiant WHERE 1=1");
	    	
	    	if (niveau != null && !niveau.isEmpty()) {
	    		sql.append(" AND niveau = ?");
	    	}
	    	if (institution != null && !institution.isEmpty()) {
	    		sql.append(" AND institution = ?");
	    	}
	    	try (Connection connection = getConnection();
	    			PreparedStatement statement = connection.prepareStatement(sql.toString())) {
	    		
	    		int parameterIndex = 1;
	    		if(niveau != null && !niveau.isEmpty()) {
	    			statement.setString(parameterIndex++, niveau);
	    		}
	    		if(institution != null && !institution.isEmpty()) {
	    			statement.setString(parameterIndex++, institution);
	    		}
	    		try (ResultSet rs = statement.executeQuery()) {
	    			while (rs.next()) {
	    				etudiant et = mapResulSetToEtudiant(rs);
	    				Etudiant.add(et);
	    			}
	    		}
	    	}
	    	return Etudiant;
	    }
	    
	    private etudiant mapResulSetToEtudiant(ResultSet rs) throws SQLException{
	    	   int id = rs.getInt("id");
	                String matricule = rs.getString("matricule");
	                String nom = rs.getString("nom");
	                String sexe = rs.getString("sexe");
	                Date datenais = rs.getDate("datenais");
	                String institution = rs.getString("institution");
	                String niveau = rs.getString("niveau");
	                String mail = rs.getString("mail");
	                String annee_univ = rs.getString("annee_univ");
			return new etudiant(id, matricule, nom, sexe, datenais, institution, niveau, mail, annee_univ);
		}

	
	    //etudiants mineurs
         public List<etudiant> getEtudiantsMineurs() throws SQLException {
	          List<etudiant> Etudiant = new ArrayList<>();
	          String sql = "SELECT * FROM etudiant WHERE DATEDIFF(CURDATE(), datenais) / 365 < 18";
	          
	          try(Connection connection = getConnection();
	        		  PreparedStatement statement = connection.prepareStatement(sql);
	        		  ResultSet rs = statement.executeQuery()) {
	        	  while (rs.next()) {
	        		  Etudiant.add(mapResulSetToEtudiant(rs));
	        		  
	        	  }
	          }
	          return Etudiant;
}
     
}
