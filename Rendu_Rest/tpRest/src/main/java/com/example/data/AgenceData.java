package com.example.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.model.Agence;
import com.example.repository.AgenceRepository;

@Configuration
public class AgenceData {
	
private Logger logger = LoggerFactory.getLogger(AgenceData.class);
	
	@Bean
	public CommandLineRunner initDataAgence(AgenceRepository agenceRepository) {
		
		return args-> {
			//ici on fait .findById; chambreRepository.findAll et aussi ajouter les instances
		logger.info("Preloding data agence : "+agenceRepository.save(
				new Agence(1,"Agence gare saint roch","123456789")));
		logger.info("Preloding data agence : "+agenceRepository.save(new Agence(2,"agence port marianne","portport123")));
		

		};

}

}
