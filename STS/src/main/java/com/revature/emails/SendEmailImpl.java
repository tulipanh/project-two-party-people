package com.revature.emails;


import java.util.HashSet;
import java.util.Set;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.revature.models.Party;
import com.revature.models.PartyPerson;

/*
 * Component for sending emails
 * Whitelist array for which addresses emails can be sent to, later can be replaced with a blacklist based on bounced emails
 * Sends a welcome email when user creates an account for the first time
 * Sends an email when an event is created
 */

@Component
public class SendEmailImpl implements SendEmail{
	
	private JavaMailSenderImpl mailSender = new com.revature.emails.JavaMailSenderImpl();
	
	public SendEmailImpl() {
		super();
		allowedEmails.add("htulipan@gmail.com");
		allowedEmails.add("jayfeldman03@gmail.com");
		allowedEmails.add("partygoer999@gmail.com");
	}

	Set<String> allowedEmails = new HashSet<>();
	String from = "therealtruepartypeople@gmail.com";

	
	private boolean checkAllowed(String to) {
		//only allows emails to be send if on whitelist, 
		//because this program does not have a way of dealing with bounced emails
		if(allowedEmails.contains(to)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean send(String to, String subject, String msg) {
		if(!checkAllowed(to)) {
			return false;
		}
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
		return true;
		
}

	@Override
	public boolean sendWelcomeEmail(PartyPerson person) {
		String to = person.getEmail();
		if(!checkAllowed(to)) {
			return false;
		}
		String subject = "Welcome to Eventify";
		String msg = String.format(
				"Hi %s!  Welcome to Eventify, the world's formost website in creating and finding fun things to do."
				, person.getUsername());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
		return true;
	}

	@Override
	public boolean sendEventCreatedEmail(PartyPerson person, Party party) {
		String to = person.getEmail();
		if(!checkAllowed(to)) {
			return false;
		}
		String subject = "Eventify Event Created";
		String msg = String.format(
				"Hi %s!  You've just created an event, %s on %s. \nYou can go ahead and invite people to your event, "
				+"or you wait and let people near you see your awesome event!"
				, person.getUsername(),party.getPartyName(), party.getPartyDate());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
		return true;
	}
	}
