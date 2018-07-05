package com.revature.emails;

import org.springframework.stereotype.Repository;

import com.revature.models.Party;
import com.revature.models.PartyPerson;

@Repository
public interface SendEmail {
	
	public boolean send(String to, String subject, String msg);
	public boolean sendWelcomeEmail(PartyPerson person);
	public boolean sendEventCreatedEmail(PartyPerson person,Party party);

}
