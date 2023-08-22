package com.tania.swe.challenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.NonNull;

//Annotation to indicate that this class is a JPA entity representing a database table
@Entity
//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class Resturant {
	// Annotation to specify that the following field is the primary key of the
	// entity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Lombok annotation to ensure that the following field cannot be null
	@NonNull
	private String name;
	
	@NonNull
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// Getter method for retrieving the restaurant's ID
	public Long getId() {
		return id;
	}

	// Setter method for setting the restaurant's ID
	public void setId(Long id) {
		this.id = id;
	}

	// Getter method for retrieving the restaurant's name
	public String getName() {
		return name;
	}

	// Setter method for setting the restaurant's name
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Resturant [id=" + id + ", name=" + name + ", username=" + username + "]";
	}

}
