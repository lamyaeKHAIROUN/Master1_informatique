package com.example.controller;

import java.util.Date;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.ws.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.core.io.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.example.exceptions.*;
import com.example.model.*;
import com.example.repository.*;

@RestController
public class ApplicationRestController {

	@Autowired
	private ChambreRepository chambreRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private AgenceRepository agenceRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private OffreRepository offreRepository;
	@Autowired
	private ClientRepository clientRepository;
	private static final String uriChambre = "/chambre";
	private static final String uriHotel = "/hotel";
	private static final String uriAgence = "/agence";
	private static final String uriOffre = "/offre";

	private List<Offre> listOffresRetourner = new ArrayList<>();

	@GetMapping(uriChambre + "/getListChambres")
	public List<Chambre> getListChambres() {
		return chambreRepository.findAll();
	}

	@GetMapping(uriHotel + "/getListHotels")
	public List<Hotel> getListHotels() {
		return hotelRepository.findAll();
	}

	@GetMapping(uriAgence + "/getListAgences")
	public List<Agence> getAllAgences() {
		return agenceRepository.findAll();
	}

	@GetMapping(uriChambre + "/{idChambre}")
	public Chambre getChambreById(@PathVariable int idChambre) throws ChambreNotFoundException {
		return chambreRepository.findById(idChambre).orElseThrow(() -> new ChambreNotFoundException(
				"Le id que vous avez donner ne correspond à aucune chambre dans la base de donnes"));
	}

	@GetMapping(uriHotel + "/{idHotel}")
	public Hotel getHotelById(@PathVariable int idHotel) throws HotelNotFoundException {
		return hotelRepository.findById(idHotel).orElseThrow(() -> new HotelNotFoundException(
				"Le id que vous avez donner ne correspond à aucune hotel dans la base de donnes"));
	}

	@GetMapping(uriChambre + "/offre/{idOffre}")
	public Optional<Offre> getOffreById(@PathVariable int idOffre) {
		return offreRepository.findById(idOffre);
	}

	@GetMapping(uriHotel + "/agences/{idDeAgence}")
	public Hotel getHotelByIdAgence(@PathVariable int idAgence) throws HotelNotFoundException {
		return hotelRepository.findById(idAgence).orElseThrow(
				() -> new HotelNotFoundException("Le id que vous avez donner ne correspond à aucune hotel"));
	}

	@GetMapping(uriAgence + "/{idAgence}")
	public Agence getAgenceById(@PathVariable int idAgence) throws AgenceNotFoundException {
		return agenceRepository.findById(idAgence).orElseThrow(
				() -> new AgenceNotFoundException("Le id que vous avez donner ne correspond à aucune agence"));
	}

	// relation chambre hotel
	@PutMapping(uriChambre + "/{idChambre}/hotel/{idHotel}")
	Chambre assignHotelToChambre(@PathVariable int idChambre, @PathVariable int idHotel) {
		Chambre chambre = chambreRepository.findById(idChambre).get();
		Hotel hotel = hotelRepository.findById(idHotel).get();
		chambre.setHotel(hotel);
		return chambreRepository.save(chambre);
	}

	//
	@PutMapping(uriChambre + "/{idReservation}/chambreId/{idChambre}")
	Reservation assignReservationToChambre(@PathVariable int idReservation, @PathVariable int idChambre) {
		Chambre chambre = chambreRepository.findById(idChambre).get();
		Reservation reservation = reservationRepository.findById(idReservation).get();
		reservation.setChambreReserver(chambre);
		return reservationRepository.save(reservation);
	}

	//
	@PutMapping(uriHotel + "/{idHotel}/agence/{idAgence}")
	public Hotel assignHotelToAgence(@PathVariable int idHotel, @PathVariable int idAgence) {
		Hotel hotel = hotelRepository.findById(idHotel).get();
		Agence agence = agenceRepository.findById(idAgence).get();
		hotel.setAgence(agence);
		return hotelRepository.save(hotel);
	}

	// fonction de recherche
	@GetMapping(uriHotel + "/findOffre/{idAgence}/nbrPers/{nbrPers}/ville/{ville}")
	public List<Offre> rechercherOffre(@PathVariable int idAgence, @PathVariable int nbrPers,
			@PathVariable String ville) {
		Agence agence = agenceRepository.findById(idAgence).get();
		Offre offre = new Offre();
		List<Offre> listOffresRetourner = new ArrayList<>();
		// List<Chambre> listChambreRetourner = new ArrayList<>();
		for (Hotel hotel : agence.getListHotels()) {
			if (hotel.getVille().equals(ville)) {
				for (Chambre chambre : hotel.getListChambres()) {
					if (chambre.getNbrPers() == nbrPers) {
						offre.setIdOffre(chambre.getIdChambre());
						offre.setChambre(chambre);
						offre.setIdOffre(chambre.getIdChambre());
						offre.setPrixTotale(chambre.getPrix() * 3);
						offre.setDateDesponabilite(chambre.getDateDisponabilite());
						// offreRepository.save(offre);
						listOffresRetourner.add(offre);
					}
				}
			}
		}
		return listOffresRetourner;

	}

	// reservation
	@GetMapping(uriChambre + "/reservationOffre/{idOffre}/client/{nomCli}/{prenom}/{carte}")
	Reservation ajouterReservation(@PathVariable int idOffre, @PathVariable String nomCli, @PathVariable String prenom,
			@PathVariable String carte) {
		int idChambre = idOffre;
		Chambre chambre = chambreRepository.findById(idChambre).get();
		Reservation reservation = new Reservation();
		List<Reservation> listReserv = new ArrayList<>();
		Client client = new Client(nomCli, prenom, carte);
		clientRepository.save(client);
		if (chambre.getEstDispo() == true) {
			int idRes = 1;
			chambre.setEstDispo(false);
			reservation.setIdReservation(idRes);
			reservation.setChambreReserver(chambre);
			reservation.setDateDebutSejour(chambre.getDateDisponabilite());
			reservation.setNbrLits(chambre.getNbrlits());
			reservation.setNbrPers(chambre.getNbrPers());
			reservation.setPrixTotal(chambre.getPrix() * 3);
			reservation.setDateFinSejour(null);
			reservation.setClient(client);
			reservationRepository.save(reservation);
			listReserv.add(reservation);
			chambre.setListeReservation(listReserv);
			chambreRepository.save(chambre);
			idRes++;
			return reservation;

		}
		return null;
	}

	// fonction de recherche
	@GetMapping("/comparateur/nbrPers/{nbrPers}/ville/{ville}")
	public List<Offre> comprateurOffre(@PathVariable int nbrPers, String ville) {
		List<Offre> offreAgence1 = new ArrayList<Offre>();
		List<Offre> offreAgence2 = new ArrayList<Offre>();
		offreAgence1 = rechercherOffre(1, nbrPers, ville);
		offreAgence1 = rechercherOffre(2, nbrPers, ville);
		Double prixOffreAgence1 = 0.00;
		Double prixOffreAgence2 = 0.00;
		for (Offre o : offreAgence1) {
			prixOffreAgence1 = o.getChambre().getPrix();

			for (Offre o1 : offreAgence2) {
				prixOffreAgence2 = o1.getChambre().getPrix();

				if (prixOffreAgence1 < prixOffreAgence2) {
					return offreAgence1;
				} else if (prixOffreAgence1 > prixOffreAgence2) {
					return offreAgence2;
				}

			}
		}
		
		return null;

	}

}