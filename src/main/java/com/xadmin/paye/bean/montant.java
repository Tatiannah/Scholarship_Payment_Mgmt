package com.xadmin.paye.bean;

public class montant {
private int id;
public montant(String niveau, int montantvalue) {
	
	this.niveau = niveau;
	this.montantvalue = montantvalue;
}

public montant(int id, String niveau, int montantvalue) {
	
	this.id = id;
	this.niveau = niveau;
	this.montantvalue = montantvalue;
}

private String niveau;
private int montantvalue;

public montant() {
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNiveau() {
	return niveau;
}

public void setNiveau(String niveau) {
	this.niveau = niveau;
}

public int getMontantvalue() {
	return montantvalue;
}

public void setMontantvalue(int montantvalue) {
	this.montantvalue = montantvalue;
}




}
