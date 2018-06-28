package com.revature.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Email extends HttpServlet{

	private static final long serialVersionUID = 1L;
    
    static final String FROM = "reimbursementhr@gmail.com";
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
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create a Properties object to contain connection configuration information.
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.auth", "true");
    	
    	HttpSession currentSession = request.getSession();
    	 final String BODY = String.join(
    	    	    System.getProperty("line.separator"),
    	    	    "<p>Your password is: "+currentSession.getAttribute("password"), 
    	    	    "<br>Please reset your password on logging in"
    	    	);
    	 final String TO = (String) currentSession.getAttribute("forgottenEmail");

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        try {
        	 // Add a configuration set header. Comment or delete the 
            // next line if you are not using a configuration set
			msg.setFrom(new InternetAddress(FROM,FROMNAME));
	        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
	        msg.setSubject(SUBJECT);
	        msg.setContent(BODY,"text/html");
	    //    msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
            
	        // Create a transport.
	        Transport transport = session.getTransport();
	        
	     // Send the message.

	        System.out.println("Sending...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        	transport.close();
        	response.sendRedirect("ValidInvalid");
			
		} catch (MessagingException e) {
			e.printStackTrace();           
             
		}
	}
}
