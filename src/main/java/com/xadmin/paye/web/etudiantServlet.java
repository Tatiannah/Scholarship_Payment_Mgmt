package com.xadmin.paye.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.paye.bean.etudiant;
import com.xadmin.paye.dao.etudiantdao;

@WebServlet("/etudiant")
public class etudiantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private etudiantdao etudiantDao;

    public void init() throws ServletException {
        etudiantDao = new etudiantdao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action3 = request.getParameter("action3");
        
        if (action3 == null) {
            action3 = ""; // Action par défaut
        }

        try {
            switch (action3) {
                case "list":
                    listEtudiants(request, response);
                    break;
                case "search":
                    searchEtudiant(request, response);
                    break;
                case "filter":
                    filterEtudiants(request, response);
                    break;
                case "mineurs":
                	listMineurs(request, response);
                	break;
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertEtudiant(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateEtudiant(request, response);
                    break;
                case "delete":
                    deleteEtudiant(request, response);
                    break;
                default:
                    listEtudiants(request, response);
            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }


	private void listEtudiants(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<etudiant> listEtudiants = etudiantDao.selectAllEtudiant();
        List<String> niveaux = etudiantDao.getAllNiveaux();
        List<String> institutions = etudiantDao.getAllInstitutions();
        
        request.setAttribute("listEtudiants", listEtudiants);
        request.setAttribute("institutions", institutions);
        request.setAttribute("niveaux", niveaux);
        
        
        request.getRequestDispatcher("etudiant-List.jsp").forward(request, response);
    }

    private void searchEtudiant(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String query = request.getParameter("query");
        List<etudiant> listEtudiants = etudiantDao.searchEtudiant(query);
        request.setAttribute("listEtudiants", listEtudiants);
        request.getRequestDispatcher("etudiant-List.jsp").forward(request, response);
    }

  
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("etudiant-form.jsp").forward(request, response);
    }

    private void insertEtudiant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
        etudiant newEtudiant = createEtudiantFromRequest(request);
        etudiantDao.insertEtudiant(newEtudiant);
        response.sendRedirect("etudiant?action3=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
int id = Integer.parseInt(request.getParameter("id"));
		
		etudiant existingEtudiant;
		try {
			existingEtudiant = etudiantDao.selectEtudiant(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("etudiant-update.jsp");
			request.setAttribute("Etudiant", existingEtudiant);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    private void updateEtudiant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
int id = Integer.parseInt(request.getParameter("id"));
		
		String matricule = request.getParameter("matricule");
		String nom = request.getParameter("nom");
		String sexe = request.getParameter("sexe");
		
		String dateStr = request.getParameter("datenais");
		java.util.Date datenais = null;
		if (dateStr != null && !dateStr.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				datenais = sdf.parse(dateStr);	
			} catch(ParseException e) {
				
			e.printStackTrace();
			}
			
		}
		if(datenais != null) {
			System.out.println("date naissance:" + datenais);
		}else {
			System.out.println("erreur");
		}
	
		
		String institution= request.getParameter("institution");
		String niveau = request.getParameter("niveau");
		String mail = request.getParameter("mail");
	    String annee_univ = request.getParameter("annee_univ");
	    
	    etudiant Etudiant = new etudiant(id, matricule, nom, sexe, (java.util.Date) datenais, institution, niveau, mail, annee_univ);
	   
	    etudiantDao.updateEtudiant(Etudiant);
	    response.sendRedirect("etudiant");
    }

    private void deleteEtudiant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        etudiantDao.deleteEtudiant(id);
        response.sendRedirect("etudiant");
    }

    private etudiant createEtudiantFromRequest(HttpServletRequest request) throws ParseException {
        String matricule = request.getParameter("matricule");
        String nom = request.getParameter("nom");
        String sexe = request.getParameter("sexe");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date datenais = sdf.parse(request.getParameter("datenais"));
        String institution = request.getParameter("institution");
        String niveau = request.getParameter("niveau");
        String mail = request.getParameter("mail");
        String annee_univ = request.getParameter("annee_univ");

        return new etudiant(matricule, nom, sexe, datenais, institution, niveau, mail, annee_univ);
    }
   private void filterEtudiants(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
     /*   String niveau = request.getParameter("niveau");
        String institution = request.getParameter("institution");
        List<etudiant> listEtudiants = etudiantDao.FilterEtudiants(niveau, institution);
        request.setAttribute("listEtudiants", listEtudiants);
        request.getRequestDispatcher("etudiant-List.jsp").forward(request, response);*/
	   String niveau = request.getParameter("niveau");
       String institution = request.getParameter("institution");

       List<etudiant> listEtudiants = etudiantDao.FilterEtudiants(niveau, institution);
       List<String> niveaux = etudiantDao.getAllNiveaux(); // Récupère tous les niveaux
       List<String> institutions = etudiantDao.getAllInstitutions(); // Récupère toutes les institutions

       request.setAttribute("listEtudiants", listEtudiants);
       request.setAttribute("niveaux", niveaux);
       request.setAttribute("institutions", institutions);

       request.getRequestDispatcher("etudiant-List.jsp").forward(request, response);
   }
    
    

    private void listMineurs(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		List<etudiant> ListEtudiants = etudiantDao.getEtudiantsMineurs();
		List<String> niveaux = etudiantDao.getAllNiveaux();
		List<String> institutions = etudiantDao.getAllInstitutions();
		  
        request.setAttribute("listEtudiants", ListEtudiants);
		request.setAttribute("institutions", institutions);
		request.setAttribute("niveaux", niveaux);
		
		 request.getRequestDispatcher("etudiant-List.jsp").forward(request, response);
		
	}
    

}