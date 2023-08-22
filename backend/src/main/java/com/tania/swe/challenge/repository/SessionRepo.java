package com.tania.swe.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tania.swe.challenge.entity.Session;

//Interface that extends JpaRepository for Session entities
//This interface provides basic CRUD operations for Session entities
public interface SessionRepo extends JpaRepository<Session, Long> {

	// Custom method declaration to retrieve a Session entity by its URL
	Session getByViewKey(String viewKey);

	Session getByAdminKey(String adminKey);

}
