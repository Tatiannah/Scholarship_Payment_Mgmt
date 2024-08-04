<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des étudiants</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Gestion de paiement de bourses</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="etudiant?action=new">Etudiant</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="montant">Montant</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="payer">Paiement</a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div class="container mt-4">

    <h3>Liste des retardataires</h3>
<br>
    <a href="retardataire" class="btn btn-outline-success my-2 my-sm-0">Envoyer notifications aux retardataires</a>
    <!-- Tableau des étudiants -->  <br><br>
    <c:if test="${not empty listeRetardataires}">
  
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Matricule</th>
                    <th>Nom</th>
                    <th>Sexe</th>
                    <th>Date de Naissance</th>
                    <th>Institution</th>
                    <th>Niveau</th>
                    <th>E-mail</th>
                    <th>Année universitaire</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="etudiant" items="${listeRetardataires}">
                    <tr>
                        <td>${etudiant.id}</td>
                        <td>${etudiant.matricule}</td>
                        <td>${etudiant.nom}</td>
                        <td>${etudiant.sexe}</td>
                        <td>${etudiant.datenais}</td>
                        <td>${etudiant.institution}</td>
                        <td>${etudiant.niveau}</td>
                        <td>${etudiant.mail}</td>
                        <td>${etudiant.annee_univ}</td>
                       
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty listeRetardataires}">
        <div class="alert alert-warning" role="alert">
            Aucun étudiant trouvé.
        </div>
    </c:if>
</div>

<footer class="footer mt-auto py-3 bg-dark text-white">
    <div class="container">
        <span class="text-muted">Exemple de gestion de paiement de bourses - Gestion des étudiants.</span>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>