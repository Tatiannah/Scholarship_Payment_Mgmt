package com.xadmin.paye.bean;

import java.util.Date;

public class etudiant {
	private int id;
	public etudiant(int id) {
		super();
		this.id = id;
	}
	private String matricule;
  
	public etudiant(String matricule, String nom, String sexe, Date datenais, String institution, String niveau,
			String mail, String annee_univ) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.sexe = sexe;
		this.datenais = datenais;
		this.institution = institution;
		this.niveau = niveau;
		this.mail = mail;
		this.annee_univ = annee_univ;
	}
	public etudiant(int id, String matricule, String nom, String sexe, Date datenais, String institution, String niveau,
			String mail, String annee_univ) {
		super();
		this.id = id;
		this.matricule = matricule;
		this.nom = nom;
		this.sexe = sexe;
		this.datenais = datenais;
		this.institution = institution;
		this.niveau = niveau;
		this.mail = mail;
		this.annee_univ = annee_univ;
	}
	public etudiant() {
		// TODO Auto-generated constructor stub
	}
	private String nom;
     private String sexe;
     private java.util.Date datenais;
     
     private String institution;
     private String niveau;
     private String mail;
     private String annee_univ;
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public java.util.Date getDatenais() {
		return datenais;
	}
	public void setDatenais(java.util.Date datenais) {
		this.datenais = datenais;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAnnee_univ() {
		return annee_univ;
	}
	public void setAnnee_univ(String annee_univ) {
		this.annee_univ = annee_univ;
	}
}
