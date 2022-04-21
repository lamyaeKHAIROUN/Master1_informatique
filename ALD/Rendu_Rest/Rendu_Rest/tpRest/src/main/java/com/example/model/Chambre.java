package com.example.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Chambre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idChambre;
	@Column(name = "nbrLits")
	private int nbrlits;
	@Column(name = "prix")
	private Double prix;
	@JsonIgnore
	@OneToMany(mappedBy = "chambreReserver",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Reservation> listeReservation;
	@Column(name = "dateDisponabilite")
	@Temporal(TemporalType.DATE)
	private Date dateDisponabilite;
	@Column(name = "nbrPers")
	private int nbrPers;
	@Column(name = "estDispo")
	private boolean estDispo = true;
	@Column(name="urlImage")
	private String urlImage;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="hotel_id",referencedColumnName="idHotel")
	private Hotel hotel;
	
	public Chambre() {}
	public Chambre(int nbrlits, Double prix, Date dateDisponabilite, int nbrPers,boolean estDispo, String url) {
		this.nbrlits = nbrlits;
		this.prix = prix;
		this.dateDisponabilite = dateDisponabilite;
		this.nbrPers = nbrPers;
		this.estDispo = estDispo;
		this.urlImage=url;
	}
	
    public List<Reservation> getListeReservation() {
		return listeReservation;
	}
	public void setListeReservation(List<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}
	public int getIdChambre() {
		return idChambre;
	}
	public void setIdChambre(int idChambre) {
		this.idChambre = idChambre;
	}
	public int getNbrlits() {
		return nbrlits;
	}
	public void setNbrlits(int nbrlits) {
		this.nbrlits = nbrlits;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public Date getDateDisponabilite() {
		return dateDisponabilite;
	}
	public void setDateDisponabilite(Date dateDisponabilite) {
		this.dateDisponabilite = dateDisponabilite;
	}
	public int getNbrPers() {
		return nbrPers;
	}
	public void setNbrPers(int nbrPers) {
		this.nbrPers = nbrPers;
	}
	public boolean getEstDispo() {
		return estDispo;
	}
	public void setEstDispo(boolean estDispo) {
		this.estDispo = estDispo;
	}
	
	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public boolean isEstDispo() {
		return estDispo;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dateDisponabilite, estDispo, idChambre,  nbrPers, nbrlits, prix);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chambre other = (Chambre) obj;
		return Objects.equals(dateDisponabilite, other.dateDisponabilite) && estDispo == other.estDispo
				&& idChambre == other.idChambre  && nbrPers == other.nbrPers
				&& nbrlits == other.nbrlits && Objects.equals(prix, other.prix);
	}
	@Override
	public String toString() {
		return "Chambre [ idChambre=" + idChambre + ", nbrlits=" + nbrlits + ", prix=" + prix
				+ ", dateDisponabilite=" + dateDisponabilite + ", urlImage "+urlImage+", nbrPers=" + nbrPers + ", estDispo=" + estDispo + "]";
	}
	
	
	

}