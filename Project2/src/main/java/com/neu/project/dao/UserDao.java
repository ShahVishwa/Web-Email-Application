package com.neu.project.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.neu.project.model.Contacts;
import com.neu.project.model.Messages;
import com.neu.project.model.User;

public class UserDao extends DAO {

	public User queryUserByNameAndPassword(String userName, String password)
            throws Exception {
		try {
			//      begin();
            Query q = getSession().createQuery("from User where userName = :userName and password = :password");
            q.setString("userName", userName);
            q.setString("password", password);
            User user = (User) q.uniqueResult();
            //       commit();
            close();
            return user;
		} catch (HibernateException e) {
			//       rollback();
            throw new Exception("Could not get user " + userName, e);
		}
    }

	
	public ArrayList<Messages> queryMessages(int userId, String folder){
		ArrayList<Messages> messageList = new ArrayList();
		Query q = getSession().createQuery("from Messages where userId = :userId and email_status= :folder");
		q.setInteger("userId", userId);
		q.setString("folder", folder);
		messageList = (ArrayList<Messages>)q.list();
		close();
		return messageList;	
	
	}
	  
	 /* 
	 * public void addMessage(String message, String fromUser, String toUser,
	 * String date) throws Exception{ try { SessionFactory sessionFactory =
	 * HibernateUtil.getSessionFactory(); Session session =
	 * sessionFactory.openSession(); Transaction tx =
	 * session.beginTransaction(); //Transaction transaction =
	 * getSession().beginTransaction(); Message dbMessage = new Message();
	 * dbMessage.setMessage(message); dbMessage.setFromUser(fromUser);
	 * dbMessage.setUserName(toUser); dbMessage.setMessageDate(date);
	 * session.save(dbMessage);
	 * System.out.println("Message is "+dbMessage.getMessage());
	 * System.out.println
	 * ("Sent by:"+dbMessage.getFromUser()+" Sent to:"+dbMessage.getUserName());
	 * System.out.println("Message date "+dbMessage.getMessageDate());
	 * 
	 * tx.commit(); close();
	 * 
	 * } catch (HibernateException e) { throw new
	 * Exception("Could not add message"); }
	 * 
	 * }
	 * 
	 * public void deleteMessagesQuery(String[] messageID) throws Exception{ try
	 * { SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	 * Session session = sessionFactory.openSession(); for(String mid :
	 * messageID) { Transaction tx = session.beginTransaction(); //Transaction
	 * transaction = getSession().beginTransaction();
	 * 
	 * String hql = "delete from  Message where messageID = :mid"; Query query =
	 * session.createQuery(hql); query.setString("mid",mid); int rowCount =
	 * query.executeUpdate(); System.out.println("Rows affected: " + rowCount);
	 * tx.commit(); } close(); } catch (HibernateException e) { throw new
	 * Exception("Could not add message"); }
	 * 
	 * }
	 */
	
	public ArrayList<Messages> draftMessages(int userId, String folder) {
		ArrayList<Messages> messageList = new ArrayList();
		Query q = getSession().createQuery("from Messages where userId = :userId and email_status= :folder");
		q.setInteger("userId", userId);
		q.setString("folder", folder);
		messageList = (ArrayList<Messages>)q.list();
		close();
		return messageList;
	}
	
	public ArrayList<Messages> sentMail(int userId, String folder) {
		ArrayList<Messages> messageList = new ArrayList();
		Query q = getSession().createQuery("from Messages where userId = :userId and email_status = :folder");
		q.setInteger("userId", userId);
		q.setString("folder", folder);
		messageList = (ArrayList<Messages>)q.list();
		close();
		return messageList;
	}
	
	public ArrayList<Messages> trash(int userId, String folder) {
		ArrayList<Messages> messageList = new ArrayList();
		Query q = getSession().createQuery("from Messages where userId = :userId and email_status = :folder");
		q.setInteger("userId", userId);
		q.setString("folder", folder);
		messageList = (ArrayList<Messages>)q.list();
		close();
		return messageList;
	}

	public void deleteContactsQuery(String[] contactID) throws Exception {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			for (String cid : contactID) {
				Transaction tx = session.beginTransaction();
				// Transaction transaction = getSession().beginTransaction();

				String hql = "delete from  Contact where contactID = :cid";
				Query query = session.createQuery(hql);
				query.setString("cid", cid);
				int rowCount = query.executeUpdate();
				System.out.println("Rows affected: " + rowCount);
				tx.commit();
			}
			close();
		} catch (HibernateException e) {
			throw new Exception("Could not add message");
		}

	}
	
	
	public void deleteMessage(int id){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Messages message= (Messages)session.get(Messages.class, new Integer(id));
			message.setEmail_status("TRASH");			
			session.save(message);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		
	}

	public ArrayList<Contacts> getContactsQuery(String userName)
			throws Exception {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			ArrayList<Contacts> contactList = new ArrayList();
			// System.out.println("User name is "+userName);
			Query q = getSession().createQuery(
					"from Contact where userName = :userName");
			q.setString("userName", userName);
			contactList = (ArrayList<Contacts>) q.list();
			// System.out.println("Number of messages is "+messageList.size());
			close();
			return contactList;

		} catch (HibernateException e) {
			throw new Exception("Could not get contacts");
		}
	}

	
	public int saveUser(User user) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return user.getUserId();
	}
	
	public void saveSentEmail(Messages messages) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(messages);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	
	
	public void starredMessage(int id){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Messages message= (Messages)session.get(Messages.class, new Integer(id));
			if(message.getEmail_status().equalsIgnoreCase("STARRED"))
				message.setEmail_status("INBOX");
			else
				message.setEmail_status("STARRED");			
			session.save(message);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		
	}
	
	public ArrayList<User> getUsers(String userRole) {
		ArrayList<User> usersList = new ArrayList();
		Query q = getSession().createQuery("from User where userRole = :userRole");
		q.setString("userRole", userRole);
		usersList = (ArrayList<User>)q.list();
		close();
		return usersList;
	}
	
	public int userSignUp(User user) throws Exception{
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			close();
		} catch(HibernateException e) {
			throw new Exception("Coud not add User");
			
		}
		return user.getUserId();
	}
	
	
	
	

}
