<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion de paiement de bourse</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: black">
        <div>
            <a href="http://www.xadmin.net">Gestion de paiement de bourses</a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Etudiant</a></li>
                  <li><a href="montant" class="nav-link">Montant</a></li>
       <li><a href="payer" class="nav-link">Payement</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
        <h2>Modifier</h2>
              <form action="<%=request.getContextPath()%>/payer?action1=update" method="post">
               
                <input type="hidden" name="id" value="${Payer.id}" />
               
                

                <fieldset class="form-group">
                    <label>Matricule :</label>
                    <input type="text" value="${Payer.matricule}" class="form-control" name="matricule" required="required">
                </fieldset>
                
                <fieldset class="form-group">
                    <label>Année universitaire :</label>
                    <input type="text" value="${Payer.annee_univ}" class="form-control" name="annee_univ" required="required">
                </fieldset>
                
                <fieldset class="form-group">
                    <label>Date :</label>
                    <input type="date" value="${Payer.datedate}" class="form-control" name="datedate" required="required">
                </fieldset>
                
                <fieldset class="form-group">
                    <label>Nombre du mois :</label>
                    <input type="number" value="${Payer.nbr_mois}" class="form-control" name="nbr_mois" required="required">
                </fieldset>

               
                <button type="submit" class="btn btn-success">Enregistrer</button>
                <button type="button" class="btn btn-danger" onclick="window.location.href='<%=request.getContextPath()%>/payer?action1=ListPayer'">Annuler</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
