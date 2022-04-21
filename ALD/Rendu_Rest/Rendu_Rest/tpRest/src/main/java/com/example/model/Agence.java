package com.example.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity 
public class Agence {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAgence;
	@Column(name = "nomAgence")
	private String nomAgence;
	@Column(name="motDePasseDeAgence")
	private String motDePasseDeAgence;	
	@JsonIgnore
	@OneToMany(mappedBy = "agence")
	private List<Hotel> listHotels ;
	public Agence() {
	}

	public Agence(int id,String nomAgence, String motDePasseDeAgence) {
		super();
		this.idAgence=id;
		this.nomAgence = nomAgence;
		this.motDePasseDeAgence = motDePasseDeAgence;
	}
	
	public List<Hotel> getListHotels() {
		return listHotels;
	}

	public void setListHotels(List<Hotel> listHotels) {
		this.listHotels = listHotels;
	}

	public int getIdAgence() {
		return idAgence;
	}
	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
	}
	public String getNomAgence() {
		return nomAgence;
	}
	public void setNomAgence(String nomAgence) {
		this.nomAgence = nomAgence;
	}
	public String getMotDePasseDeAgence() {
		return motDePasseDeAgence;
	}
	public void setMotDePasseDeAgence(String motDePasseDeAgence) {
		this.motDePasseDeAgence = motDePasseDeAgence;
	}
	
	@Override
	public String toString() {
		return "Agence [idAgence=" + idAgence + ", nomAgence=" + nomAgence + ", motDePasseDeAgence="
				+ motDePasseDeAgence + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idAgence, motDePasseDeAgence, nomAgence);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agence other = (Agence) obj;
		return idAgence == other.idAgence && Objects.equals(motDePasseDeAgence, other.motDePasseDeAgence)
				&& Objects.equals(nomAgence, other.nomAgence);
	}
	

}
