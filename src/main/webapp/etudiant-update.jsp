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
                 <form action="<%=request.getContextPath()%>/etudiant?action3=update" method="post">
                <input type="hidden" name="id" value="${Etudiant.id}" />

                <fieldset class="form-group">
                    <label>Matricule :</label>
                    <input type="text" value="${Etudiant.matricule}" class="form-control" name="matricule" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Noms :</label>
                    <input type="text" value="${Etudiant.nom}" class="form-control" name="nom" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Sexe :</label><br>
                    <input type="radio" value="Masculin" name="sexe" ${Etudiant.sexe == 'Masculin' ? 'checked' : ''}> Masculin
                    <input type="radio" value="Feminin" name="sexe" ${Etudiant.sexe == 'Feminin' ? 'checked' : ''}> Féminin
                </fieldset>

                <fieldset class="form-group">
                    <label>Date de naissance :</label>
                    <input type="date" value="${Etudiant.datenais}" class="form-control" name="datenais" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Institution :</label>
                    <input type="text" value="${Etudiant.institution}" class="form-control" name="institution" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Niveau :</label>
                    <select name="niveau" class="form-control">
                        <option value="L1" ${Etudiant.niveau == 'L1' ? 'selected' : ''}>Licence 1</option>
                        <option value="L2" ${Etudiant.niveau == 'L2' ? 'selected' : ''}>Licence 2</option>
                        <option value="L3" ${Etudiant.niveau == 'L3' ? 'selected' : ''}>Licence 3</option>
                        <option value="M1" ${Etudiant.niveau == 'M1' ? 'selected' : ''}>Master 1</option>
                        <option value="M2" ${Etudiant.niveau == 'M2' ? 'selected' : ''}>Master 2</option>
                    </select>
                </fieldset>

                <fieldset class="form-group">
                    <label>E-mail :</label>
                    <input type="email" value="${Etudiant.mail}" class="form-control" name="mail" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Année universitaire :</label>
                    <input type="text" value="${Etudiant.annee_univ}" class="form-control" name="annee_univ" required="required">
                </fieldset>

                <button type="submit" class="btn btn-success">Enregistrer</button>
                <button type="button" class="btn btn-danger" onclick="window.location.href='<%=request.getContextPath()%>/list'">Annuler</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
