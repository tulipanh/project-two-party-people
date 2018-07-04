package com.revature.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	
private static final long serialVersionUID = 1L;
    
    static final String FROM = "therealtruepartypeople@gmail.com";
    static final String FROMNAME = "HR";
    static final String SMTP_USERNAME = "AKIAJYJMK3HVJXQO3XTA";
    static final String SMTP_PASSWORD = "AhyV+CAd6xueUaQ8IXvsH/l7hqsnMH0vvAtyjtGWY3ku";
    
    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
  //  static final String CONFIGSET = "ConfigSet";
    
    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    // See http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    static final String HOST = "email-smtp.us-east-1.amazonaws.com";
    
    // The port you will connect to on the Amazon SES SMTP endpoint. 
    static final int PORT = 587;
    
    static final String SUBJECT = "Forgotten Password";
	
	@PostMapping
	public boolean sendEmail(String recipient, String header, String body) {
		
		
		return false;
	}

}
