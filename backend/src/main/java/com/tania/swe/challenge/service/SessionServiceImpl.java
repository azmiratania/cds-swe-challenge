package com.tania.swe.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tania.swe.challenge.entity.Session;
import com.tania.swe.challenge.repository.SessionRepo;

@Service
public class SessionServiceImpl implements SessionService {

	// Autowired annotation to inject SessionRepo dependency
	@Autowired
	SessionRepo sessionRepo;

	// Transactional annotation to manage transactions for the method
	@Transactional
	public void save(Session session) {
		sessionRepo.save(session);
	}

	// Method implementation to retrieve a Session entity by its URL
	@Transactional
	public Session getByViewKey(String viewKey) {

		return sessionRepo.getByViewKey(viewKey);
	}

	@Transactional
	public Session getByAdminKey(String adminKey) {
		// TODO Auto-generated method stub
		 return sessionRepo.getByAdminKey(adminKey);
	}

}
