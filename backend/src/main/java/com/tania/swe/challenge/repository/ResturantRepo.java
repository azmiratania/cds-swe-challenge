package com.tania.swe.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tania.swe.challenge.entity.Resturant;

//Interface that extends JpaRepository for Restaurant entities
//This interface provides basic CRUD operations for Restaurant entities
public interface ResturantRepo extends JpaRepository<Resturant, Long> {

}
