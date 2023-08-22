package com.tania.swe.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tania.swe.challenge.entity.Resturant;
import com.tania.swe.challenge.repository.ResturantRepo;

//Service implementation for the RestaurantService interface
@Service
public class ResturantServiceImpl implements ResturantService {

	// Autowired annotation to inject ResturantRepo dependency
	@Autowired
	ResturantRepo resturantRepo;

	// Method implementation to save a Restaurant entity
	@Override
	public void save(Resturant resturant) {
		resturantRepo.save(resturant);

	}

}
