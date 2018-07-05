package com.revature.emails;

import java.util.Properties;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

@Repository
public class JavaMailSenderImpl extends org.springframework.mail.javamail.JavaMailSenderImpl{
	
	
	
	public JavaMailSenderImpl() {
		super();
		this.setHost("email-smtp.us-east-1.amazonaws.com");
	    this.setPort(587);
	    this.setUsername("AKIAJYJMK3HVJXQO3XTA");
	    this.setPassword("AhyV+CAd6xueUaQ8IXvsH/l7hqsnMH0vvAtyjtGWY3ku");
	     
	    Properties props = this.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	}

	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("email-smtp.us-east-1.amazonaws.com");
	    mailSender.setPort(587);
	    mailSender.setUsername("AKIAJYJMK3HVJXQO3XTA");
	    mailSender.setPassword("AhyV+CAd6xueUaQ8IXvsH/l7hqsnMH0vvAtyjtGWY3ku");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	   
	    return mailSender;
	}


	@Override
	public String toString() {
		return "JavaMailSenderImpl [getJavaMailProperties()=" + getJavaMailProperties() + ", getSession()="
				+ getSession() + ", getProtocol()=" + getProtocol() + ", getHost()=" + getHost() + ", getPort()="
				+ getPort() + ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword()
				+ ", getDefaultEncoding()=" + getDefaultEncoding() + ", getDefaultFileTypeMap()="
				+ getDefaultFileTypeMap() + ", createMimeMessage()=" + createMimeMessage() +"]";
	}
	
	
}
