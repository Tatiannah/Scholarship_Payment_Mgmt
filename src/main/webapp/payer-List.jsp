<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@ page import="com.xadmin.paye.bean.montant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Voir les montants du bourse</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: black">
            <div>
                <a href="http://ww.xadmin.net">Gestion de paiement de bourses</a>
            </div>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Etudiant</a></li>
                      <li><a href="montant" class="nav-link">Montant</a></li>
       <li><a href="payer" class="nav-link">Payement</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="row">
        <div class="container">
            <h3 class="text">Liste de payements</h3>
            <hr>
                 <form action="payer" method="get" class="form-inline mb-3">
 <input type="hidden" name="action1" value="listeRetardataires">
 <label for="mois" class="mr-2">Mois: </label>
 <select name="nbr_mois" id="nbr_mois" class="form-control mr-sm-2">
          <option value="1">1 Mois</option>
           <option value="2">2Mois</option>
           <option value="3">3Mois</option>
           <option value="4">4Mois</option>
            <option value="5">5Mois</option>
            <option value="6">6Mois</option>
             <option value="7">7Mois</option>
             <option value="8">8Mois</option>
              <option value="9">9Mois</option>
               <option value="10">10Mois</option>
               <option value="11">11Mois</option>
                <option value="12">12Mois</option>
 </select>
 <button type="submit" class="btn btn-outline-success my-2 my-sm-0">liste de retardataires</button>
 </form>
    
            <div class="container text-left">
                <a href="payer-form.jsp" class="btn btn-primary">Ajouter une nouvelle payement</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Matricule</th>
                        <th>Annee universitaire</th>
                        <th>Date</th>
                        <th>Nombre du mois</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="payer" items="${ListPayers}">
                        <tr>
                            <td>${payer.id}</td>
                            <td>${payer.matricule}</td>
                            <td>${payer.annee_univ}</td>
                            <td>${payer.datedate}</td>
                            <td>${payer.nbr_mois}</td>
                            <td>
                          <a href="payer?action1=genererpdf&id=${payer.id}" class="btn btn-success btn-sm">PDF</a>
                                <a href="payer?action1=edit&id=${payer.id}" class="btn btn-danger btn-sm">Modifier</a>
                               
                                <a href="payer?action1=delete&id=${payer.id}" class="btn btn-warning btn-sm">Supprimer</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
           
        </div>
    </div>
    <footer class="footer mt-auto py-3 bg-dark text-white">
    <div class="container">
        <span class="text-muted">Gestion de paiement de bourses - 2023-2024.</span>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
