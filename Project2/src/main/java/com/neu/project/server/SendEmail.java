package com.neu.project.server;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.neu.project.model.Messages;
import com.neu.project.model.User;

public class SendEmail {
	
	public static void email(User user, Messages msgs ) {
		
	      // Recipient's email ID needs to be mentioned.
	      String to = msgs.getReceiver();//change accordingly
	      System.out.println("Receiver is " + msgs.getReceiver());
	      // Sender's email ID needs to be mentioned
	      String from = user.getUserName();//change accordingly
	      System.out.println("Sender is " + user.getUserName());
	      final String username = user.getUserName();//change accordingly
	      System.out.println("UserName is " + user.getUserName());
	      final String password = user.getPassword();//change accordingly
	      System.out.println("password is " + user.getPassword());

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session emailSession = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(emailSession);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));
	        // message.addRecipient(Message.RecipientType.BCC, new InternetAddress(""));
	     //message.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
	         // Set Subject: header field
	         message.setSubject(msgs.getSubject());

	      // Now set the actual message
	         
	         //Add attachment
	         MimeBodyPart messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setText(msgs.getContent());
	         System.out.println("part1");
	         
	         Multipart multipart = new MimeMultipart();
	         multipart.addBodyPart(messageBodyPart);
	         System.out.println("part2");
	         
	         messageBodyPart = new MimeBodyPart();
	         System.out.println("part3");
	        
	              
	         String file = "C:/Users/Vishwa/Pictures/PIA17011_ip.jpg";
	         String fileName = "picture";
	         DataSource source = new FileDataSource(file);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(fileName);
	         multipart.addBodyPart (messageBodyPart);

	         message.setContent(multipart);
	         System.out.println("part4");
	      


	         // Send message
	         try{
	         Transport.send(message);
	         } catch(MessagingException e) {
	        	 e.printStackTrace();
	         }

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
		}

}
