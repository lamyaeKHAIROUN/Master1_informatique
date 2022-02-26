package com.example.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.model.Hotel;
import com.example.repository.HotelRepository;

@Configuration

public class HotelData {
	private Logger logger = LoggerFactory.getLogger(HotelData.class);
	
 	@Bean
	public CommandLineRunner initDataHotel(HotelRepository hotelRepository) {
		
		return args-> {
			 
			//ici on fait .findById; chambreRepository.findAll et aussi ajouter les instances
			logger.info("Preloding data hotel : "+hotelRepository.save(new Hotel("squad", "france", "montpellier", "rue joseph",  3)));
			logger.info("Preloding data hotel : "+hotelRepository.save(new Hotel("tirara", "france", "montpellier", "rue doria",  3)));
			logger.info("Preloding data hotel : "+hotelRepository.save(new Hotel("adagio", "france", "montpellier", "avenue de fes",  3)));
		    logger.info("Preloding data hotel : "+hotelRepository.save(new Hotel("ibiza", "france", "montpellier", "impasse de l epi", 4)));
		    logger.info("Preloding data hotel : "+hotelRepository.save(new Hotel("azur", "france", "sete", "avenue de lodeve", 5)));
		    logger.info("Preloding data hotel : "+hotelRepository.save(new Hotel("H20", "france", "avignon", "rue de la republique", 2)));

		};

}
}