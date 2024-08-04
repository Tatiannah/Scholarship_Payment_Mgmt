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
                <li><a href="etudiant" class="nav-link">Etudiant</a></li>
                      <li><a href="montant" class="nav-link">Montant</a></li>
       <li><a href="payer" class="nav-link">Payement</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="row">
        <div class="container">
            <h3 class="text">Montant du bourse</h3>
            <hr>
            <div class="container text-left">
                <a href="montant-form.jsp" class="btn btn-success">Ajouter un nouveau montant</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Niveau</th>
                        <th>Montant</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="montant" items="${ListMontants}">
                        <tr>
                            <td>${montant.id}</td>
                            <td>${montant.niveau}</td>
                            <td>${montant.montantvalue}</td>
                            <td>
                                <a href="montant?action=edit&id=${montant.id}" class="btn btn-warning btn-sm">Modifier</a>
                               
                                <a href="montant?action=delete&id=${montant.id}" class="btn btn-danger btn-sm">Supprimer</a>
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
