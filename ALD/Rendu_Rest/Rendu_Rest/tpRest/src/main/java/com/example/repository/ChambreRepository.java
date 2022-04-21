package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Chambre;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Integer>{

}
