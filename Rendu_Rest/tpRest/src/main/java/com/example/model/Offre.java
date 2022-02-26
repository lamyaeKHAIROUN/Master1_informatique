package com.example.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Offre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOffre;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idOffre")
	private Chambre chambre;
	@Column(name = "dateDisponabilite")
	@Temporal(TemporalType.DATE)
	private Date dateDesponabilite;
	@Column(name="prixTotale")
	private Double prixTotale;
	public int getIdOffre() {
		return idOffre;
	}
	public void setIdOffre(int idOffre) {
		this.idOffre = idOffre;
	}
	public Chambre getChambre() {
		return chambre;
	}
	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}
	public Date getDateDesponabilite() {
		return dateDesponabilite;
	}
	public void setDateDesponabilite(Date dateDesponabilite) {
		this.dateDesponabilite = dateDesponabilite;
	}
	public Double getPrixTotale() {
		return prixTotale;
	}
	public void setPrixTotale(Double prixTotale) {
		this.prixTotale = prixTotale;
	}
	
  	

}
