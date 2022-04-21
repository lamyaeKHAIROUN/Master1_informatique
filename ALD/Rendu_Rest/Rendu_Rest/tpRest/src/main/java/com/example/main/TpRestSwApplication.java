package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@EntityScan(basePackages = {
		"com.example.model"
})
@EnableJpaRepositories(basePackages = {
		"com.example.repository"
})

@SpringBootApplication(scanBasePackages= {
		"com.example.data",
		"com.example.exceptions",
		"com.example.controller"
})

//aller chercher dans autres packages
public class TpRestSwApplication {
	/*@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	return sessionFactory;
	}*/
	public static void main(String[] args) {
		SpringApplication.run(TpRestSwApplication.class);
	}
	

}

