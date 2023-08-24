/*
 * This class represents a Spring REST controller that handles various actions related to a restaurant selection session.
 * It provides endpoints for initiating a session, submitting restaurant choices, viewing available restaurants, and ending a session.
 */

package com.tania.swe.challenge.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tania.swe.challenge.dto.ResturantDto;
import com.tania.swe.challenge.entity.Resturant;
import com.tania.swe.challenge.entity.Session;
import com.tania.swe.challenge.service.ResturantService;
import com.tania.swe.challenge.service.SessionService;

@RestController
//RequestMapping accepts POST, GET, PUT, PATCH, and DELETE
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ApiController {

	@Autowired
	ResturantService resturantService;

	@Autowired
	SessionService sessionService;

	@PostMapping("/initiate")
	public Session initiateSession(@RequestParam("name") String name) {

		Session session = new Session();

		session.setName(name);

		sessionService.save(session);

		return session;
	}

	// Endpoint for submitting a selected restaurant for a session
	@PostMapping("/submit/{viewKey}")
	public String submitResturant(@PathVariable("viewKey") String viewKey,
			@ModelAttribute("Resturant") Resturant resturantDto) {

		try {

			String resturantName = resturantDto.getName();
			Boolean validateResturantName = resturantName.matches("^[A-Za-z0-9 \\.\\-\\&]*$");

			if (!resturantName.isEmpty() && resturantName.length() < 20 && validateResturantName) {
				Resturant resturant = new Resturant();
				resturant.setName(resturantDto.getName());
				resturant.setUsername(resturantDto.getUsername());
				resturantService.save(resturant);

				Session session = sessionService.getByViewKey(viewKey);

				if (session != null && session.isActive()) {
					session.addResturant(resturant);
					sessionService.save(session);
					return "OK";
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "FAIL";
	}

	// Endpoint for viewing available restaurants in a session
	@GetMapping("/view/{viewKey}")
	public ResturantDto view(@PathVariable("viewKey") String viewKey) {

		ResturantDto resturantDto = new ResturantDto();

		Session session = sessionService.getByViewKey(viewKey);

		if (session != null) {
			if (!session.isActive()) {
				resturantDto.setSelectedResturant(session.getSelectedResturant());
			}
			resturantDto.setIsSessionActive(session.isActive());
			Collection<Resturant> resturants = session.getResturant();

			for (Resturant resturant : resturants) {
				resturantDto.addResturant(resturant.getName());
			}

		}

		return resturantDto;
	}

	// Endpoint for ending a session and selecting a random restaurant
	@GetMapping("/end/{adminKey}")
	public Session end(@PathVariable("adminKey") String adminKey) {

		Session session = sessionService.getByAdminKey(adminKey);

		if (session != null && session.isActive()) {

			session.deactivate();
			session.setEndTime(LocalDateTime.now());
			sessionService.save(session);

			Collection<Resturant> resturants = session.getResturant();
			List<String> resturantList = new ArrayList<>();
			for (Resturant resturant : resturants) {
				resturantList.add(resturant.getName());
			}

			if (resturantList == null || resturantList.size() == 0) {
				session.setSelectedResturant("McDonalds");
				sessionService.save(session);
				return session;
			} else {
				int size = resturantList.size();
				int choice = new Random().nextInt(size);

				session.setSelectedResturant(resturantList.get(choice));
				sessionService.save(session);
				return session;
			}
		}

		return null;

	}
}
