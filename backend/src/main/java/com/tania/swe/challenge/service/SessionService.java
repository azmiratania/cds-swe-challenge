package com.tania.swe.challenge.service;

import com.tania.swe.challenge.entity.Session;

public interface SessionService {

	 void save(Session session);
	 
	 Session getByViewKey(String viewKey);

	Session getByAdminKey(String adminKey);
	
	
	
}
