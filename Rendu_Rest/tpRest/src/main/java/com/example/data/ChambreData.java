package com.example.data;

import java.text.SimpleDateFormat;

import org.slf4j.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.model.Chambre;
import com.example.repository.ChambreRepository;



//s'execute la premiere lors du lancement du serveur
@Configuration
public class ChambreData {
	
	private Logger logger = LoggerFactory.getLogger(ChambreData.class);
	SimpleDateFormat formater=new SimpleDateFormat("yyyy-mm-dd");
	
	@Bean
	public CommandLineRunner init(ChambreRepository chambreRepository) {
		
		return args-> {
			//ici on fait .findById; chambreRepository.findAll et aussi ajouter les instances
			logger.info("Preloding : "+chambreRepository.save(new Chambre(2,45.00,formater.parse("2021-11-29"),1,true,"https://www.hoteldelaplage.com/wp-content/uploads/2018/05/H%C3%B4tel-de-la-Plage-Bordagain-3.jpg")));
			logger.info("Preloding : "+chambreRepository.save(new Chambre(1,25.00,formater.parse("2021-30-12"),2,true,"https://plagepalace.com/wp-content/uploads/2019/07/HOTEL_04_Plage-Palace.png")));
			logger.info("Preloding : "+chambreRepository.save(new Chambre(2,22.00,formater.parse("2021-29-12"),3,true,"https://www.hotel-design-secret-de-paris.com/wp-content/uploads/2015/01/secret-de-paris-chambre-trocadero-21-md1.jpg")));
			logger.info("Preloding : "+chambreRepository.save(new Chambre(3,50.00,formater.parse("2022-29-01"),4,true,"https://hotel-balthazar.com/_novaimg/3679001-1147511_0_88_1600_979_980_600.jpg")));
			logger.info("Preloding : "+chambreRepository.save(new Chambre(1,30.00,formater.parse("2022-09-01"),1,true,"https://www.hoteldelaplage.com/wp-content/uploads/2018/05/H%C3%B4tel-de-la-Plage-Bordagain-3.jpg")));
			logger.info("Preloding : "+chambreRepository.save(new Chambre(1,35.00,formater.parse("2022-21-01-"),2,true,"https://plagepalace.com/wp-content/uploads/2019/07/HOTEL_04_Plage-Palace.png")));
			logger.info("Preloding : "+chambreRepository.save(new Chambre(2,70.00,formater.parse("2021-29-11"),3,true,"https://www.hotel-design-secret-de-paris.com/wp-content/uploads/2015/01/secret-de-paris-chambre-trocadero-21-md1.jpg")));
			logger.info("Preloding : "+chambreRepository.save(new Chambre(4,150.00,formater.parse("2021-29-12"),4,true,"https://hotel-balthazar.com/_novaimg/3679001-1147511_0_88_1600_979_980_600.jpg")));
			logger.info("Preloding : "+chambreRepository.save(new Chambre(2,30.00,formater.parse("2021-22-12"),2,true,"https://plagepalace.com/wp-content/uploads/2019/07/HOTEL_04_Plage-Palace.png")));
			logger.info("Preloding : "+chambreRepository.save(new Chambre(1,15.00,formater.parse("2021-29-11"),2,true,"https://www.hoteldelaplage.com/wp-content/uploads/2018/05/H%C3%B4tel-de-la-Plage-Bordagain-3.jpg")));

			

		};
		
	}

}