package com.example.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idReservation;
	@Column(name = "dateDebutSejour")
	private Date dateDebutSejour;
	@Column(name = "dateFinSejour")
	private Date dateFinSejour;
	@Column(name = "nbrLits")
	private int nbrLits;
	@Column(name = "nbrPers")
	private int nbrPers;
	@Column(name = "prixTotal")
	private Double prixTotal;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="chambre_id",referencedColumnName="idChambre")
	private Chambre chambreReserver;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="client_id",referencedColumnName="idClient")
	private Client client;
	
	public Chambre getChambreReserver() {
		return chambreReserver;
	}
	public void setChambreReserver(Chambre chambreReserver) {
		this.chambreReserver = chambreReserver;
	}
	public Reservation() {
		super();
	}
	public Reservation(Date dateDebutSejour, Date dateFinSejour, int nbrLits, int nbrPers,
			Double prixTotal, Client client) {
		super();
		this.dateDebutSejour = dateDebutSejour;
		this.dateFinSejour = dateFinSejour;
		this.nbrLits = nbrLits;
		this.nbrPers = nbrPers;
		this.prixTotal = prixTotal;
		this.client = client;
	}
	public int getIdReservation() {
		return idReservation;
	}
//
	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}
	public Date getDateDebutSejour() {
		return dateDebutSejour;
	}
	public void setDateDebutSejour(Date dateDebutSejour) {
		this.dateDebutSejour = dateDebutSejour;
	}
	public Date getDateFinSejour() {
		return dateFinSejour;
	}
	public void setDateFinSejour(Date dateFinSejour) {
		this.dateFinSejour = dateFinSejour;
	}
	public int getNbrLits() {
		return nbrLits;
	}
	public void setNbrLits(int nbrLits) {
		this.nbrLits = nbrLits;
	}
	public int getNbrPers() {
		return nbrPers;
	}
	public void setNbrPers(int nbrPers) {
		this.nbrPers = nbrPers;
	}
	public Double getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
	