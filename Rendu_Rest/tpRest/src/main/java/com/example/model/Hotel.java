package com.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Hotel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idHotel;
	@Column(name = "nomHotel")
	private String nomHotel;
	@JsonIgnore
	@OneToMany(mappedBy = "hotel")
	private List<Chambre> listChambres;
	@Column(name = "pays")
	private String pays;
	@Column(name = "ville")
	private String ville;
	@Column(name = "rue")
	private String rue;
	
	@Column(name = "nbEtoile")
	private int nbEtoile;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idDeAgence",referencedColumnName="idAgence")
	private Agence agence;
	public Hotel() {}
	public Hotel(String nomHotel, Chambre chambreRes, String pays,
			String ville, String rue, int nbEtoile) {
		super();
		this.nomHotel = nomHotel;
	//	this.listChambres = listChambres;
	//	this.chambreRes = chambreRes;
		this.pays = pays;
		this.ville = ville;
		this.rue = rue;
		this.nbEtoile = nbEtoile;
	}
	
	public Hotel(String nomHotel, String pays, String ville, String rue, int nbEtoile) {
		this.nomHotel = nomHotel;
		this.pays = pays;
		this.ville = ville;
		this.rue=rue;
		this.nbEtoile = nbEtoile;
	}
	
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	public int getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}
	public String getNomHotel() {
		return nomHotel;
	}
	public void setNomHotel(String nomHotel) {
		this.nomHotel = nomHotel;
	}
	public List<Chambre> getListChambres() {
		return listChambres;
	}
	public void setListChambres(List<Chambre> listChambres) {
		this.listChambres = listChambres;
	}
	/*public Chambre getChambreRes() {
		return chambreRes;
	}
	public void setChambreRes(Chambre chambreRes) {
		this.chambreRes = chambreRes;
	}*/
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	public int getNbEtoile() {
		return nbEtoile;
	}
	public void setNbEtoile(int nbEtoile) {
		this.nbEtoile = nbEtoile;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idHotel, nbEtoile, nomHotel, pays,  rue, ville);
	}
	@Override
	public String toString() {
		return "Hotel [idHotel=" + idHotel + ", nomHotel=" + nomHotel + ", pays=" + pays + ", ville=" + ville + ", rue="
				+ rue + ", positionGps=" + ", nbEtoile=" + nbEtoile + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		return idHotel == other.idHotel && nbEtoile == other.nbEtoile && Objects.equals(nomHotel, other.nomHotel)
				&& Objects.equals(pays, other.pays) 
				&& Objects.equals(rue, other.rue) && Objects.equals(ville, other.ville);
	}
}