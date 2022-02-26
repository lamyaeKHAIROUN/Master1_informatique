package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Offre;

public interface OffreRepository extends JpaRepository <Offre, Integer> {

	
}
