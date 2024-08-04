package com.xadmin.paye.bean;

import java.time.LocalDate;


public class payer {
private int id;
private String matricule;
private String annee_univ;
private LocalDate datedate;

public payer(String matricule, String annee_univ, LocalDate datedate, int nbr_mois) {
	super();
	this.matricule = matricule;
	this.annee_univ = annee_univ;
	this.datedate = datedate;
	this.nbr_mois = nbr_mois;
}
public LocalDate getDatedate() {
	return datedate;
}
public void setDatedate(LocalDate datedate) {
	this.datedate = datedate;
}
private int nbr_mois;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMatricule() {
	return matricule;
}
public void setMatricule(String matricule) {
	this.matricule = matricule;
}
public String getAnnee_univ() {
	return annee_univ;
}
public void setAnnee_univ(String annee_univ) {
	this.annee_univ = annee_univ;
}

public int getNbr_mois() {
	return nbr_mois;
}
public void setNbr_mois(int nbr_mois) {
	this.nbr_mois = nbr_mois;
}
public payer(int id, String matricule, String annee_univ, LocalDate datedate, int nbr_mois) {
	super();
	this.id = id;
	this.matricule = matricule;
	this.annee_univ = annee_univ;
	this.datedate = datedate;
	this.nbr_mois = nbr_mois;
}
public payer() {
	// TODO Auto-generated constructor stub
}

}
