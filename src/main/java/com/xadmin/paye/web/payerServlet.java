package com.xadmin.paye.web;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.paye.bean.etudiant;
import com.xadmin.paye.bean.payer;
import com.xadmin.paye.dao.payerdao;

import java.util.List;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.OutputStream;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Locale;


/**
 * Servlet implementation class etudiantServlet
 * @param <Date>
 
 */
@WebServlet("/payer")
public class payerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private payerdao PayerDao;
    

    public void init() {
        PayerDao = new payerdao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action1 = request.getParameter("action1");
        if(action1 == null) {
        	action1 = "";
        }
        try {
        switch (action1) {
            case "new":
                showNewForm(request, response);
                break;

            case "insert":
                try {
                    insertPayer(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erreur lors de l'insertion d'un montant", e);
                }
                break;

            case "delete":
                try {
                    deletePayer(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erreur lors de la suppression d'un montant", e);
                }
                break;

            case "edit":
                try {
                    showEditFormPayer(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erreur lors de l'affichage du formulaire de modification", e);
                }
                break;
            case "listeRetardataires":
                try {
                    listeRetardataires(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erreur lors de la mise à jour d'un montant", e);
                }
                break;
            case "listeRe":
                try {
                    listeRe(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erreur lors de la mise à jour d'un montant", e);
                }
                break;
            case "genererpdf":
                try {
                    genererpdf(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erreur lors de la mise à jour d'un montant", e);
                }
                break;
           

            case "update":
                try {
                    updatePayer(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erreur lors de la mise à jour d'un montant", e);
                }
                break;

            default:
                
                    ListPayer(request, response);
                    break;
                }
                
                } catch (SQLException ex) {
                    throw new ServletException(ex);
                }
                
        
        
    }
  




	private void ListPayer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	
			List<payer> ListPayer = PayerDao.selectAllPayer();
			request.setAttribute("ListPayers", ListPayer);
			RequestDispatcher dispatcher = request.getRequestDispatcher("payer-List.jsp");
			dispatcher.forward(request, response);
		
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("payer-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditFormPayer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
  		int id = Integer.parseInt(request.getParameter("id"));
  		
  		payer existingPayer = PayerDao.selectPayer(id);
  			RequestDispatcher dispatcher = request.getRequestDispatcher("payer-update.jsp");
  			request.setAttribute("Payer", existingPayer);
  			dispatcher.forward(request, response);
  		
      }
    
    private void insertPayer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	
		String matricule = request.getParameter("matricule");
		String annee_univ = request.getParameter("annee_univ");
		String dateStr = request.getParameter("datedate");
		
		LocalDate datedate = null;
		if(dateStr != null && !dateStr.isEmpty()) {
			try {
				datedate = LocalDate.parse(dateStr);
			} catch(DateTimeParseException e) {
				e.printStackTrace();
				throw new ServletException("erreur", e);
			}
		}
		
		
		int nbr_mois = Integer.parseInt(request.getParameter("nbr_mois"));

	    
	    payer newPayer = new payer(matricule, annee_univ, datedate , nbr_mois);
	   
	    PayerDao.insertPayer(newPayer);
	    response.sendRedirect("payer");
    }

    private void updatePayer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	
		
		String matricule = request.getParameter("matricule");
		String annee_univ = request.getParameter("annee_univ");
		String dateStr = request.getParameter("datedate");
	/*	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = dateFormat.parse(dateStr);
		java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());*/

		LocalDate datedate = null;
		if(dateStr != null && !dateStr.isEmpty()) {
			try {
				datedate = LocalDate.parse(dateStr);
			} catch(DateTimeParseException ex) {
				ex.printStackTrace();
				
			}
		}
		
	
		int nbr_mois = Integer.parseInt(request.getParameter("nbr_mois"));
	   
	    
	    payer payerObj = new payer(id, matricule, annee_univ, datedate, nbr_mois);
	   
	    PayerDao.updatePayer(payerObj);
	    response.sendRedirect("payer");
    }

   
    private void deletePayer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	 int id = Integer.parseInt(request.getParameter("id"));
		
			 PayerDao.deletePayer(id);
		
		 response.sendRedirect("payer");
    }

    

   	private void listeRetardataires(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException  {
   		
   		String moisCible = request.getParameter("nbr_mois");
   		
   		int nbr_mois = Integer.parseInt(moisCible);
   		List<etudiant> retardataire = PayerDao.selectEtudiantTard(nbr_mois);
   		request.setAttribute("listeRetardataires", retardataire);
   		
   		List<etudiant> retardataires = PayerDao.getRetardataires(nbr_mois);
   		request.setAttribute("listeRetardataires", retardataires);
   		RequestDispatcher dispatcher = request.getRequestDispatcher("retardataire-List.jsp");
	
			dispatcher.forward(request, response);
	
   	}
	private void listeRe(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException  {
   		
   		String moisCible = request.getParameter("nbr_mois");
   		
   		int nbr_mois = Integer.parseInt(moisCible);
   		List<etudiant> listeRe = PayerDao.selectEtudiantTard(nbr_mois);
   		request.setAttribute("listeRetardataires", listeRe);
   		
   	
   		RequestDispatcher dispatcher = request.getRequestDispatcher("retardataire-List.jsp");
	
			dispatcher.forward(request, response);
	
   	}

	private void genererpdf(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        payer Payer = PayerDao.getPayementById(id);
        if (Payer == null || Payer.getDatedate() == null ) {
        	throw new ServletException("Payer or date must be not null");
        }
        etudiant Etudiant = PayerDao.getEtudiantByMatricule(Payer.getMatricule());
        int montantvalue = PayerDao.getMontantByNiveau(Etudiant.getNiveau());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf");

        try {
            OutputStream out = response.getOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Adding title
            document.add(new Paragraph("Reçu du paiement de bourse"));
            document.add(new Paragraph(" ")); // Empty line

            // Adding date
            document.add(new Paragraph("Aujourd'hui le " + new SimpleDateFormat("dd MMM yyyy", Locale.FRENCH).format(new java.util.Date())));
            document.add(new Paragraph(" ")); // Empty line

            // Adding student details
            document.add(new Paragraph("Matricule : " + Etudiant.getMatricule()));
            document.add(new Paragraph("Nom : " + Etudiant.getNom()));
            document.add(new Paragraph("Née le : " + new SimpleDateFormat("dd MMM yyyy", Locale.FRENCH).format(Etudiant.getDatenais())));
            document.add(new Paragraph("Sexe : " + Etudiant.getSexe()));
            document.add(new Paragraph("Institution : " + Etudiant.getInstitution()));
            document.add(new Paragraph("Niveau : " + Etudiant.getNiveau()));
            document.add(new Paragraph(" ")); // Empty line

            // Adding payment details in a table
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            PdfPCell c1 = new PdfPCell(new Phrase("Mois"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Montant"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            table.setHeaderRows(1);

            table.addCell("Equipement");
            table.addCell("110.000");

            // Adding payment months dynamically
            Calendar cal = Calendar.getInstance();
            LocalDate date = Payer.getDatedate();

            for (int i = Payer.getNbr_mois(); i > 0; i--) {
                String mois = date.minusMonths(i).format(DateTimeFormatter.ofPattern("MMMM", Locale.FRENCH));
                table.addCell(mois);
                table.addCell(String.format("%,d", montantvalue));
                cal.add(Calendar.MONTH, 1);
            }

            document.add(table);

            // Calculating total
            int total = 110000 + Payer.getNbr_mois() * montantvalue;
            document.add(new Paragraph("Total : " + String.format("%,d Ariary", total)));
            document.add(new Paragraph(" ")); // Empty line

            // Adding total paid
            //document.add(new Paragraph("Total Payé : " + String.format("%,d Ariary", total)));

            document.close();
            
            response.sendRedirect("payer");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

		

   	
}
