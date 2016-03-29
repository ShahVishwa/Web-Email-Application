package com.neu.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Messages{
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int messageID;
	private String sender;
	private String content;
	private String subject;
	private  Date date;
	private String receiver;
	private String email_status;

	
	//@ManyToOne
	//@JoinColumn(name="email_ID")
	//private EmailAccounts account;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	public int getMessageID() {
		return messageID;
	}


	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public String getEmail_status() {
		return email_status;
	}


	public void setEmail_status(String email_status) {
		this.email_status = email_status;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	//public EmailAccounts getAccount() {
		//return account;
	//}


	//public void setAccount(EmailAccounts account) {
		//this.account = account;
	//}
	
	
	
	
	
	
	

}
