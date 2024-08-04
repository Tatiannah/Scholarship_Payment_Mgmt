<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.xadmin.paye.bean.montant" %>
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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
           
           
           
           <h2>Ajouter Montant </h2>
       <form action="<%=request.getContextPath()%>/montant?action=insert" method="post">
       
       
            <fieldset class="form-group">
                <label>Niveau :</label>
                <select name="niveau" class="form-control">
                     <option value="L1">Licence 1</option>
                      <option value="L2">Licence 2</option>
                       <option value="L3">Licence 3</option>
                        <option value="M1">Master 1</option>
                         <option value="M2">Master 2</option>
                </select>
            </fieldset>
            <fieldset class="form-group">
                <label>Montant :</label>
                <input type="number" class="form-control" name="montantvalue" required="required">
            </fieldset>
            <button type="submit" class="btn btn-success" value="Sauvegarder">Enregistrer</button>
            <button type="button" class="btn btn-danger" onclick="window.location.href='<%=request.getContextPath()%>/montant?action=ListMontant'">Annuler</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
