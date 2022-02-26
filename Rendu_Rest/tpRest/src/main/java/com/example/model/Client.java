package com.example.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;


@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idClient;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "carteCredit")
	private String carteCredit;
	@OneToMany(mappedBy = "client",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Reservation> listeReservation;
	
	public Client(){}

	public Client( String nom, String prenom, String carteCredit) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.carteCredit = carteCredit;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCarteCredit() {
		return carteCredit;
	}

	public void setCarteCredit(String carteCredit) {
		this.carteCredit = carteCredit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carteCredit, idClient, nom, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(carteCredit, other.carteCredit) && idClient == other.idClient
				&& Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom);
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", carteCredit=" + carteCredit
				+ "]";
	}
	

}