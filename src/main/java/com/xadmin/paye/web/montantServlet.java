package com.xadmin.paye.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xadmin.paye.bean.montant;
import com.xadmin.paye.dao.montantdao;

import java.util.List;



/**
 * Servlet implementation class etudiantServlet
 * @param <Date>
 */
@WebServlet("/montant")
public class montantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private montantdao MontantDao;
    

    public void init() {
        MontantDao = new montantdao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) {
        	action = "";
        }
        try {
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;

            case "insert":
                try {
                    insertMontant(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erreur lors de l'insertion d'un montant", e);
                }
                break;

            case "delete":
                try {
                    deleteMontant(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erreur lors de la suppression d'un montant", e);
                }
                break;

            case "edit":
                try {
                    showEditFormMontant(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erreur lors de l'affichage du formulaire de modification", e);
                }
                break;

            case "update":
                try {
                    updateMontant(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erreur lors de la mise à jour d'un montant", e);
                }
                break;

            default:
                
                    ListMontant(request, response);
                    break;
                }
                
                } catch (SQLException ex) {
                    throw new ServletException(ex);
                }
                
        
        
    }
    private void ListMontant(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	
			List<montant> ListMontant = MontantDao.selectAllMontant();
			request.setAttribute("ListMontants", ListMontant);
			RequestDispatcher dispatcher = request.getRequestDispatcher("montant-List.jsp");
			dispatcher.forward(request, response);
		
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("montant-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditFormMontant(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
  		int id = Integer.parseInt(request.getParameter("id"));
  		
  		montant existingMontant = MontantDao.selectMontant(id);
  			RequestDispatcher dispatcher = request.getRequestDispatcher("montant-update.jsp");
  			request.setAttribute("Montant", existingMontant);
  			dispatcher.forward(request, response);
  		
      }
    
    private void insertMontant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	
		String niveau = request.getParameter("niveau");
		int montantvalue = Integer.parseInt(request.getParameter("montantvalue"));

	    
	    montant newMontant = new montant(niveau, montantvalue);
	   
	    MontantDao.insertMontant(newMontant);
	    response.sendRedirect("montant");
    }

    private void updateMontant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	
		
		String niveau = request.getParameter("niveau");
		int montantvalue = Integer.parseInt(request.getParameter("montantvalue"));
	   
	    
	    montant montantObj = new montant(id, niveau, montantvalue);
	   
	    MontantDao.updateMontant(montantObj);
	    response.sendRedirect("montant");
    }

   
    private void deleteMontant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	 int id = Integer.parseInt(request.getParameter("id"));
		
			 MontantDao.deleteMontant(id);
		
		 response.sendRedirect("montant");
    }

  

}
