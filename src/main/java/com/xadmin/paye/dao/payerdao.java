package com.xadmin.paye.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.xadmin.paye.bean.etudiant;
import com.xadmin.paye.bean.payer;

public class payerdao {
	 private static final String JDBC_URL = "jdbc:mysql://localhost:3305/payement";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "admin";
	    private static final String INSERT_PAYER_SQL = "INSERT INTO `payer` (`matricule`, `annee_univ`, `datedate`, `nbr_mois`) VALUES (?, ?, ?, ?)";
	    private static final String SELECT_PAYER_BY_ID = "SELECT id,matricule, annee_univ, datedate, nbr_mois FROM payer WHERE id =?";
	    private static final String SELECT_PAYER_ALL = "SELECT * FROM payer";
	    private static final String DELETE_PAYER_SQL = "DELETE FROM payer WHERE id = ?;";
	  
	    public payerdao() {}

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


	    public void insertPayer(payer payer) throws SQLException {
	    	   System.out.println(INSERT_PAYER_SQL);
		        Connection connection = getConnection();
		        if (connection == null) {
		            System.err.println("La connexion à la base de données n'a pas pu être établie.");
		            return;
		        }
		        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYER_SQL)) {
		            preparedStatement.setString(1, payer.getMatricule());
		            preparedStatement.setString(2, payer.getAnnee_univ());
		            preparedStatement.setObject(3, payer.getDatedate());
		            preparedStatement.setInt(4, payer.getNbr_mois());
		            
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
	    // SELECT ALL
	    public List<payer> selectAllPayer() {
	        List<payer> Payer = new ArrayList<>();
	       try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYER_ALL);) {
	    	   ResultSet rs = preparedStatement.executeQuery();
	    	   while(rs.next()) {
	    		   int id = rs.getInt("id");
	    		   String matricule = rs.getString("matricule");
	    		   String annee_univ = rs.getString("annee_univ");
	    		   LocalDate datedate = rs.getObject("datedate", LocalDate.class);
	    		   int nbr_mois = rs.getInt("nbr_mois");
	    		   Payer.add(new payer(id, matricule, annee_univ, datedate, nbr_mois));
	    		   
	    	   } 
	       }
	    	   catch (SQLException e) {
	    		   e.printStackTrace();
	    	   }

	    	   return Payer;
	      
	    }


	    // SELECT
	    public payer selectPayer(int id) {
	        payer Payer = null;
	        Connection connection = getConnection();
	        if (connection == null) {
	            System.err.println("La connexion à la base de données n'a pas pu être établie.");
	            return null;
	        }
	        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYER_BY_ID)) {
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                String matricule = rs.getString("matricule");
	                String annee_univ = rs.getString("annee_univ");
	                LocalDate datedate = rs.getObject("datedate", LocalDate.class);
	                int nbr_mois = rs.getInt("nbr_mois");
	              
	                Payer = new payer(id, matricule, annee_univ, datedate, nbr_mois);
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
	        return Payer;
	    }

	
	    // UPDATE
	    public void updatePayer(payer Payer) throws SQLException {
	        String UPDATE_PAYER_SQL = "UPDATE payer SET matricule = ?, annee_univ =?, datedate =?, nbr_mois =? WHERE id = ?;";
	        try (Connection connection = getConnection();
	       
	        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PAYER_SQL)) {
	       
	            preparedStatement.setString(1, Payer.getMatricule());
	            preparedStatement.setString(2, Payer.getAnnee_univ());
	            preparedStatement.setObject(3, Payer.getDatedate());
	            preparedStatement.setInt(4, Payer.getNbr_mois());
	            preparedStatement.setInt(5, Payer.getId());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	    }

	    // DELETE
	    public boolean deletePayer(int id) throws SQLException {
	        boolean rowDeleted;
	        Connection connection = getConnection();
	        if (connection == null) {
	            System.err.println("La connexion à la base de données n'a pas pu être établie.");
	            return false;
	        }
	        try (PreparedStatement statement = connection.prepareStatement(DELETE_PAYER_SQL)) {
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
	    //liste retardataires 
	    public List<etudiant> getRetardataires(int moisCible) throws SQLException {
	    	List<etudiant> retardataires = new ArrayList<>();
	    	LocalDate currentDate = LocalDate.now();
	    	LocalDate targetDate = currentDate.minusMonths(moisCible);
	    	String sql = "SELECT e.* " +
	    			"FROM etudiant e " + 
	    			"LEFT JOIN payer p ON e.matricule = p.matricule " +
	    			"AND p.datedate + INTERVAL p.nbr_mois MONTH >= ? " +
	    			"WHERE p.id IS NULL OR (p.datedate + INTERVAL p.nbr_mois MONTH < ? AND p.datedate + INTERVAL p.nbr_mois MONTH >= ?) ";
	    	try (Connection connection = getConnection();
	    			PreparedStatement statement = connection.prepareStatement(sql)) {	
	    		statement.setDate(1, java.sql.Date.valueOf(targetDate));
	    		statement.setDate(2, java.sql.Date.valueOf(targetDate));
	    		statement.setDate(3, java.sql.Date.valueOf(targetDate.minusMonths(moisCible)));
	    		try (ResultSet rs= statement.executeQuery()) {
	    			while(rs.next()) {
	    				etudiant etud = new etudiant(
	    						rs.getInt("id"),
	    						rs.getString("matricule"),
	    						rs.getString("nom"),
	    						rs.getString("sexe"),
	    						rs.getDate("datenais"),
	    						rs.getString("institution"),
	    						rs.getString("niveau"),
	    						rs.getString("mail"),
	    						rs.getString("annee_univ")
	    						);
	    						retardataires.add(etud);
	    			}
	    		}
	    	}
	    	return retardataires;
	    }
	    public List<etudiant> selectEtudiantTard(int nbr_mois) throws SQLException {
	    	List<etudiant> retardataire = new ArrayList<>();
	    
	    	String sql = "SELECT e.* " +
	    			"FROM etudiant e " + 
	    			"LEFT JOIN payer p ON e.matricule = p.matricule " +
	    			"WHERE p.id IS NULL OR (p.datedate + INTERVAL p.nbr_mois MONTH < CURDATE()) ";
	    	try (Connection connection = getConnection();
	    			PreparedStatement statement = connection.prepareStatement(sql)) {	
	    		
	    		try (ResultSet rs= statement.executeQuery()) {
	    			while(rs.next()) {
	    				etudiant etud = new etudiant(
	    						rs.getInt("id"),
	    						rs.getString("matricule"),
	    						rs.getString("nom"),
	    						rs.getString("sexe"),
	    						rs.getDate("datenais"),
	    						rs.getString("institution"),
	    						rs.getString("niveau"),
	    						rs.getString("mail"),
	    						rs.getString("annee_univ")
	    						);
	    						retardataire.add(etud);
	    			}
	    		}
	    	}
	    	return retardataire;
	    }
	    
	    
	    //generer un pdf 
	    public payer getPayementById(int id) {
	    	payer Payement = null;
	    	try {
	    		Connection connection = getConnection();
	    		PreparedStatement ps = connection.prepareStatement("SELECT * FROM payer WHERE id =?");
	    	ps.setInt(1, id);
	    	ResultSet rs = ps.executeQuery();
	    	   while (rs.next()) {
	    		   Payement = new payer ();
	    		   Payement.setId(rs.getInt("id"));
	    		   Payement.setMatricule(rs.getString("matricule"));
	    		   Payement.setAnnee_univ(rs.getString("annee_univ"));
	    		   LocalDate date = rs.getObject("datedate", LocalDate.class);
	    		   if (date == null) {
	    			   throw new SQLException("Date must not be null");
	    		   }
	    		   Payement.setDatedate(date);
	    		   Payement.setNbr_mois(rs.getInt("nbr_mois"));
	    		   
	    	   }
	    		   
	    	   }catch (Exception e) {
	    		   e.printStackTrace();
	    	   }
	    	return Payement;
	    	
	    }
	    
	    
	    
	    public etudiant getEtudiantByMatricule(String matricule) {
	    	etudiant Etudiant = null;
	    	try {
	    		Connection connection = getConnection();
	    		PreparedStatement ps = connection.prepareStatement("SELECT * FROM etudiant WHERE matricule =?");
	    		ps.setString(1, matricule);
		    	ResultSet rs = ps.executeQuery();
		    	   while (rs.next()) {
		    		   Etudiant = new etudiant ();
		    		   Etudiant.setId(rs.getInt("id"));

		    		   Etudiant.setNom(rs.getString("nom"));
		    		   Etudiant.setMatricule(rs.getString("matricule"));
		    		   Etudiant.setSexe(rs.getString("sexe"));
		    		   Etudiant.setDatenais(rs.getDate("datenais"));
		    		   Etudiant.setInstitution(rs.getString("institution"));
		    		   Etudiant.setNiveau(rs.getString("niveau"));
		    		   Etudiant.setMail(rs.getString("mail"));
		    		   Etudiant.setAnnee_univ(rs.getString("annee_univ"));
		    	   }
		    		   
		    	   }catch (Exception e) {
		    		   e.printStackTrace();
		    	   }
		    	return Etudiant;
		    	
		    }
	    
	    public int getMontantByNiveau(String niveau) {
	    	int montant = 0;
	    	try {
	    		Connection connection = getConnection();
	    		PreparedStatement ps = connection.prepareStatement("SELECT montantvalue FROM montant WHERE niveau = ?");
	    		ps.setString(1, niveau);
	    		ResultSet rs =ps.executeQuery();
	    		if(rs.next()) {
	    			montant = rs.getInt("montantvalue");
	    		}
	    		
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	return montant;
	    }
	    
}
	    	
	  

