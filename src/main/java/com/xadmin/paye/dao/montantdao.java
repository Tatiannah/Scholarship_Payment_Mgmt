package com.xadmin.paye.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.paye.bean.montant;

public class montantdao {
	 private static final String JDBC_URL = "jdbc:mysql://localhost:3305/payement";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "admin";
	    private static final String INSERT_MONTANT_SQL = "INSERT INTO `montant` (`niveau`, `montantvalue`) VALUES (?, ?)";
	    private static final String SELECT_MONTANT_BY_ID = "SELECT id,niveau, montantvalue FROM montant WHERE id =?";
	    private static final String SELECT_MONTANT_ALL = "SELECT * FROM montant";
	    private static final String DELETE_MONTANT_SQL = "DELETE FROM montant WHERE id = ?;";

	    public montantdao() {}

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


	    public void insertMontant(montant montant) throws SQLException {
	    	   System.out.println(INSERT_MONTANT_SQL);
		        Connection connection = getConnection();
		        if (connection == null) {
		            System.err.println("La connexion à la base de données n'a pas pu être établie.");
		            return;
		        }
		        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MONTANT_SQL)) {
		            preparedStatement.setString(1, montant.getNiveau());
		            preparedStatement.setInt(2, montant.getMontantvalue());
		            
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
	    public List<montant> selectAllMontant() {
	        List<montant> Montant = new ArrayList<>();
	       try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MONTANT_ALL);) {
	    	   ResultSet rs = preparedStatement.executeQuery();
	    	   while(rs.next()) {
	    		   int id = rs.getInt("id");
	    		   String niveau = rs.getString("niveau");
	    		   int montantvalue = rs.getInt("montantvalue");
	    		   Montant.add(new montant(id, niveau, montantvalue));
	    		   
	    	   } 
	       }
	    	   catch (SQLException e) {
	    		   e.printStackTrace();
	    	   }

	    	   return Montant;
	      
	    }


	    // SELECT
	    public montant selectMontant(int id) {
	        montant Montant = null;
	        Connection connection = getConnection();
	        if (connection == null) {
	            System.err.println("La connexion à la base de données n'a pas pu être établie.");
	            return null;
	        }
	        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MONTANT_BY_ID)) {
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                String niveau = rs.getString("niveau");
	                Integer montantvalue = rs.getInt("montantvalue");
	              
	                Montant = new montant(id, niveau, montantvalue);
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
	        return Montant;
	    }

	
	    // UPDATE
	    public void updateMontant(montant Montant) throws SQLException {
	        String UPDATE_MONTANT_SQL = "UPDATE montant SET niveau = ?, montantvalue =? WHERE id = ?;";
	        try (Connection connection = getConnection();
	       
	        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MONTANT_SQL)) {
	       
	            preparedStatement.setString(1, Montant.getNiveau());
	            preparedStatement.setInt(2, Montant.getMontantvalue());
	            preparedStatement.setInt(3, Montant.getId());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	    }

	    // DELETE
	    public boolean deleteMontant(int id) throws SQLException {
	        boolean rowDeleted;
	        Connection connection = getConnection();
	        if (connection == null) {
	            System.err.println("La connexion à la base de données n'a pas pu être établie.");
	            return false;
	        }
	        try (PreparedStatement statement = connection.prepareStatement(DELETE_MONTANT_SQL)) {
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
}
