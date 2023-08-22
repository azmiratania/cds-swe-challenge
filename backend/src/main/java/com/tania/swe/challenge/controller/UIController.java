package com.tania.swe.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//RequestMapping accepts POST, GET, PUT, PATCH, and DELETE
@RequestMapping(path = "/")
public class UIController {

	@GetMapping("/")
	public String home() {
		return "frontend/home";
	}
}
