package com.xadmin.paye.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.paye.bean.EmailUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;

@WebServlet("/retardataire")
public class retardataireServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3305/payement";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "admin";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> retardataires = getRetardataires();

        for (String email : retardataires) {
            try {
                String subject = "Notification de retard de paiement";
                String body = "Cher étudiant,<br>Votre paiement de bourse est en retard depuis trois semaines.";
                EmailUtil.sendEmail(email, subject, body);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("retardataire");
    }

    private List<String> getRetardataires() {
        List<String> emails = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Connexion à la base de données
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            // Requête SQL pour récupérer les emails des retardataires
            String sql = "SELECT e.nom, e.mail " +
                         "FROM etudiant e " +
                         "LEFT JOIN payer p ON e.matricule = p.matricule " +
                         "WHERE p.datedate < DATE_SUB(NOW(), INTERVAL 3 WEEK)";

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String email = rs.getString("mail");
                emails.add(email);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return emails;
    }
    	
}
