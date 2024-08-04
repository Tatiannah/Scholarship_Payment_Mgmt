<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des étudiants</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
        <style>
        .btn-group-left {
            display: flex;
            justify-content: flex-start; /* Aligner à gauche */
            align-items: center; /* Centrer verticalement */
            gap: 10px; /* Espace entre les boutons */
            margin-top: 20px;
        }

        .btn-group-left .btn-primary,
        .btn-group-left .btn-success {
            margin: 0; /* Réinitialiser les marges pour éviter les écarts */
        }
    /* Espacement des cellules du tableau */
        .table td, .table th {
            padding: 15px;
            vertical-align: middle;
        }

        /* Styliser la colonne des actions */
        .table-actions {
            display: flex;
            gap: 10px; /* Espacement entre les boutons */
        }

        /* Réduire les marges des boutons */
        .btn {
            margin: 0;
        }

        /* Aligner le contenu dans les cellules de la colonne "Actions" */
        .table-actions a {
            display: inline-block;
        }

        /* Espacer les colonnes pour un meilleur rendu visuel */
        .table th, .table td {
            white-space: nowrap;
        }

        /* Bordure plus fine pour les colonnes */
        .table th, .table td {
            border: 1px solid #dee2e6;
        }

        /* Conteneur pour centrer le tableau */
        .table-container {
            display: flex;
            justify-content: center; /* Centre le tableau horizontalement */
        }

        /* Largeur maximale pour le tableau */
        .table {
            max-width: 180%; /* Ajustez cette valeur selon vos besoins */
            margin: auto;
        /* Centre le tableau horizontalement */
        }    </style>
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
                 <li class="nav-item">
                    <a class="nav-link" href="retardataire-List.jsp">Retardataires</a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div class="container">
   <br>
    <h3>Liste des étudiants</h3>

    <!-- Formulaire de recherche -->
    <form class="form-inline mb-3" action="etudiant" method="GET">
        <input type="hidden" name="action3" value="search">
        <input class="form-control mr-sm-2" type="text" name="query" placeholder="Rechercher par nom">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Rechercher</button>
    </form>

    <!-- Formulaire de filtrage -->
    <form class="form-inline mb-3" action="<%=request.getContextPath()%>/etudiant?action3=filter" method="GET">
        <input type="hidden" name="action3" value="filter">
        
        
        
        <label class="mr-2">Filtrer par niveau :</label>
        <select class="form-control mr-sm-2" name="niveau">
            <option value="">Tous les niveaux</option>
            <c:forEach var="niveau" items="${niveaux}">
                <option value="${niveau}">${niveau}</option>
            </c:forEach>
        </select>

        <label class="mr-2">Filtrer par université :</label>
        <select class="form-control mr-sm-2" name="institution">
            <option value="">Tous les univérsités</option>
            <c:forEach var="institution" items="${institutions}">
                <option value="${institution}">${institution}</option>
            </c:forEach>
        </select>
        <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Filtrer</button>
    </form>
   
 <div class="container">
        <!-- Groupe de boutons alignés à gauche -->
        <div class="btn-group-left">
            <form action="etudiant" method="get">
                <input type="hidden" name="action3" value="mineurs">
                <button type="submit" class="btn btn-primary">Lister les étudiants mineurs</button>
            </form>
            <a href="etudiant-form.jsp" class="btn btn-success">Ajouter</a>
        </div>
    </div>
    <br>
    <!-- Tableau des étudiants -->
    <c:if test="${not empty listEtudiants}">
     <div class="table-container">
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
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="etudiant" items="${listEtudiants}">
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
                        <td class="table-actions">
                            <a href="etudiant?action3=edit&id=${etudiant.id}" class="btn btn-warning btn-sm">Modifier</a>
                            
                            <a href="etudiant?action3=delete&id=${etudiant.id}" class="btn btn-danger btn-sm">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
    </c:if>
    <c:if test="${empty listEtudiants}">
        <div class="alert alert-warning" role="alert">
            Aucun étudiant trouvé.
        </div>
    </c:if>
</div>
  <br>  <br>  <br>
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