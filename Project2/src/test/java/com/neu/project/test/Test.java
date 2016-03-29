package com.neu.project.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.neu.project.model.Contacts;
import com.neu.project.model.Messages;
import com.neu.project.model.User;

public class Test {
	
	public static void main(String[] args) {
   	 User user = new User();
   	 user.setUserName("emailclient2015@gmail.com");
   	 user.setPassword("webemailclient");
   	 user.setFirstName("Vishwa");
   	 user.setLastName("Shah");
   	 user.setGender("Female");
   	 user.setBirthDate("04/24/1993");
   	 user.setPhoneNo("6173204604");
   	 user.setCity("Boston");
   	 user.setState("Massachusetts");
   	 user.setCountry("USA");
   	 user.setUserRole("User");
   	 user.setUserStatus("Approved");
   	 
   	 User user1 = new User();
   	 user1.setUserName("mananjayeshshah@gmail.com");
  	 user1.setPassword("mananshah");
  	 user1.setFirstName("Manan");
  	 user1.setLastName("Shah");
  	 user1.setGender("Male");
  	 user1.setBirthDate("12/01/1988");
  	 user1.setPhoneNo("3153787761");
  	 user1.setCity("San Diego");
  	 user1.setState("CA");
  	 user1.setCountry("USA");
  	 user1.setUserRole("Admin");
  	 
  	 User user2 = new User();
  	 user2.setUserName("daxa.jmv@gmail.com");
 	 user2.setPassword("daxashah");
 	 user2.setFirstName("Daxa");
 	 user2.setLastName("Shah");
 	 user2.setGender("Female");
 	 user2.setBirthDate("09/22/1964");
 	 user2.setPhoneNo("9428602964");
 	 user2.setCity("Ahmedabad");
 	 user2.setState("Gujarat");
 	 user2.setCountry("India");
 	 user2.setUserRole("User");
   	 
   	 
   	 Messages msgs = new Messages();
   	 //user.getMsgs().add(msgs);
   	 //user.getMsgs().add(msgs2);
   	 
   	 Contacts contacts = new Contacts();
   	 //contacts.setName("Karishma Dalal");
   	 //contacts.setEmailAccount("dalalk21@gmail.com");
  	 
  	 user.getContacts().add(contacts);
  	 
  	 user1.getContacts().add(contacts);

  	 user2.getContacts().add(contacts);

   	 
   	 user.getMessages().add(msgs);
   	
   	 SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
   	 Session session = sessionFactory.openSession();
   	 session.beginTransaction();
   	 session.save(user);
   	 session.save(msgs);
   	 session.save(contacts);
   	 session.save(user1);
   	 session.save(user2);
   	 session.getTransaction().commit();
   	 session.close();
   	 
   	 
    }

}
