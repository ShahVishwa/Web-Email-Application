package com.neu.project;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.project.dao.UserDao;
/*import com.neu.project.model.EmailAccount;*/
import com.neu.project.model.Messages;
import com.neu.project.model.User;
import com.neu.project.server.EMailData;
import com.neu.project.server.Fetcher;
import com.neu.project.server.SendEmail;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	@Qualifier("userValidator")
	private Validator validator;

	@Autowired
	private UserDao userDao;

	/*
	 * This is to initialize webDataBinder,set its validator as we specify.
	 */
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public String submitForm(User user, HttpServletRequest request, Model model){
		//model.addAttribute("user", user);
		String returnVal = "home";
		System.out.println("User is :" + user.getUserName());
		System.out.println("Password is :" + user.getPassword());
		//System.out.println("Result is :" + result);
		/*if(result.hasErrors()){
			//modelMap.put(BindingResult.class.getName() + ".user", result);
			return "login1";
		}else{*/
			try {
				User u = userDao.queryUserByNameAndPassword(user.getUserName(), user.getPassword());
				System.out.println("Username is :" + u.getUserName());
				System.out.println("password is :" + u.getPassword());
				HttpSession session = request.getSession();
				session.setAttribute("user", u);
				if(u!=null){
					//model.addAttribute("User", user);
					//System.out.println("In login method "+ user.getUserName());
					//model.addAttribute("userName", user.getUserName());
					
					if(u.getUserRole().equals("Admin")) {
						System.out.println("Admin is :" + u.getUserRole());
						List<User> usersList = userDao.getUsers("User");
						System.out.println("users are is :" + usersList);
						model.addAttribute("usersList",usersList);
						String returnVal2= "adminPage";
						return returnVal2;
					}
				
					return returnVal;
					
					//model.addAttribute("contacts", user.getContacts());
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}
		//return "loginError";
		//return returnVal;
			return "loginErrorPage";
	}

	/*@RequestMapping(value = "/", method = RequestMethod.POST)
	public String dashboard(Model model, User user, BindingResult result,
			HttpServletRequest request) {
		String returnVal = "home";
		if (result.hasErrors()) {
			return "homePage";
		} else {
			try {
				User u = userDao.queryUserByNameAndPassword(user.getUserName(),
						user.getPassword());
				if (u != null) {
					HttpSession session = request.getSession();
					session.setAttribute("user", u);
					// model.addAttribute("user", u);
					if (user.getUserName().equalsIgnoreCase("admin")) {
						return "adminPage";
					} else {
						return returnVal;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// return "loginError";
			}
			// return "home";
			return "loginErrorPage";
		}
	}*/
	
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public String userSignUp(@ModelAttribute("user") User user, HttpServletRequest request, @RequestParam(required = false) String q) {
		String returnval = "home";
		try{
		//User user = new User();
		String userName= request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String birthDate = request.getParameter("birthDate");
		String phoneNo = request.getParameter("phoneNo");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		
		user.setUserName(userName);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setBirthDate(birthDate);
		user.setPhoneNo(phoneNo);
		user.setCity(city);
		user.setState(state);
		user.setCountry(country);
		user.setUserRole("User");
		userDao.userSignUp(user);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		} catch(Exception e) {
			 e.printStackTrace();
		}
		return returnval;
	}

	/*@RequestMapping(value = "/userSignup", method = RequestMethod.POST)
	public String userSignup(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String q, Model model) {
		String returnVal = "redirect:home";

		try {
			User user = new User();
			String userName = request.getParameter("userName");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String password = request.getParameter("password");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String year = request.getParameter("year");

			Date birthDate = new Date();
			birthDate.setYear(Integer.parseInt(year) - 1900);
			System.out.println(year + "-" + month + "-" + day);
			birthDate.setMonth(Integer.parseInt(month) - 1);
			birthDate.setDate(Integer.parseInt(day));

			String emailPassword = request.getParameter("emailPassword");
			String emailAccount = request.getParameter("emailAccount");
			String gender = request.getParameter("gender");

			user.setUserName(userName);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setGender(gender);
			user.setUser_Status("");
			user.setBirthDate(birthDate);
			userDao.saveUser(user);
			
			//EntityManager.flush();
			
			EMailData ed=new EMailData();
			ed.userId = user.getUserID();
            ed.emailAccount = account.getEmailAccount();
            ed.emailPassword = account.getEmailPassword();
            ed.email_id = account.getEmail_Id();
            Fetcher fetcher  = new Fetcher();
            fetcher.getEmails(ed);
            

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return "loginError";
		}
		// return "home";
		return returnVal;

	}*/
	
	@RequestMapping(value="/inbox")
	public String showMessages(Model model, HttpSession session) {
		try{
		//Integer userId = (Integer)session.getAttribute("userId");
		User u = (User)session.getAttribute("user");
		System.out.println("UserId is" + u.getUserId());
		List<Messages> messageList = userDao.queryMessages(u.getUserId(), "INBOX");
		System.out.println("Inbox Messages are: " + messageList.size());
		model.addAttribute("messages", messageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "inbox";
	}

	/*@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public String getInbox(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserID(),
				"INBOX");
		System.out.println("Messages for userId: " + user.getUserID()
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);
		return "inbox";
	}*/

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getDashboard(Locale locale, Model model) {

		return "dashboard";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String getSignUpPage(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "signUp";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getTestPage(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "test";
	}
	
	@RequestMapping(value="/drafts", method=RequestMethod.GET)
	public String drafts(Model model, HttpSession session) {
		User u = (User)session.getAttribute("user");
		System.out.println("userId id:" + u.getUserId());
		List<Messages> messageList = userDao.draftMessages(u.getUserId(), "Drafts");
		System.out.println("Draft Messages are:" + messageList.size());
		model.addAttribute("messages", messageList);
		return "drafts";
	}

	/*@RequestMapping(value = "/drafts", method = RequestMethod.GET)
	public String getDrafts(Locale locale, Model model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserID(),
				"DRAFTS");
		System.out.println("Messages for userId: " + user.getUserID()
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);
		return "drafts";
	}*/

	/*@RequestMapping(value = "/starred", method = RequestMethod.GET)
	public String getStarred(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserId(),
				"STARRED");
		System.out.println("Messages for userId: " + user.getUserId()                           STARRED STARRED STARRED
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);

		return "starred";
	}*/

	/*@RequestMapping(value = "/spam", method = RequestMethod.GET)
	public String getSpam(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserID(),
				"SPAM");
		System.out.println("Messages for userId: " + user.getUserID()
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);

		return "spam";
	}*/
	
	@RequestMapping(value="/trash", method=RequestMethod.GET)
	public String trash(Model model, HttpSession session) {
		User u = (User)session.getAttribute("user");
		System.out.println("userId is:" + u.getUserId());
		List<Messages> messageList = userDao.trash(u.getUserId(), "Trash");
		System.out.println("Trash Mails are:" + messageList.size());
		model.addAttribute("messages", messageList);
		return "trash";
	}

	/*@RequestMapping(value = "/trash", method = RequestMethod.GET)
	public String getTrash(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserID(),
				"TRASH");
		System.out.println("Messages for userId: " + user.getUserID()
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);

		return "trash";
	}*/

	@RequestMapping(value="/outbox", method=RequestMethod.GET)
	public String sentMail(Model model, HttpSession session) {
		User u = (User)session.getAttribute("user");
		System.out.println("userId is:" + u.getUserId());
		List<Messages> messageList = userDao.sentMail(u.getUserId(), "Sent Mail");
		System.out.println("Sent Mails are:" + messageList.size());
		model.addAttribute("messages", messageList);
		return "outbox";
	}
	
	/*@RequestMapping(value = "/outbox", method = RequestMethod.GET)
	public String getOutbox(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserID(),
				"STARRED");
		System.out.println("Messages for userId: " + user.getUserID()
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);

		return "outbox";
	}*/
	
	@RequestMapping(value="/sendMessage", method=RequestMethod.GET)
	public void composeMail(Model model, HttpServletRequest request) {
		try{
			Messages msgs = new Messages();
			String to = request.getParameter("receiver");
			String subject = request.getParameter("subject");
			String message = request.getParameter("content");
			System.out.println("Receiver is:" + to);
			System.out.println("Subject is:" + subject);
			System.out.println("Content is:" + message);
			
			
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("user");
			session.setAttribute("user", u);
			
			/*if(message.contains("PFA") || message.contains("Attachment") || subject.contains("PFA")) {
				return "";
			}*/
			System.out.println("User is:" + u.getUserName());
			msgs.setUser(u);
			msgs.setSender(u.getUserName());
			msgs.setReceiver(to);
			msgs.setSubject(subject);
			msgs.setContent(message);
			
			//u.getMessages().add(msgs);
			//msgs.setUser(u);
			
			SendEmail sendEmail = new SendEmail();
			sendEmail.email(u, msgs);
			
			/*SendEmail sendEmail = new SendEmail();
			sendEmail.email(u, msgs);*/
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*@RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
	public void sendMail(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String receiver = request.getParameter("receiver");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		Messages message = new Messages();
		message.setContent(content);
		message.setDate(new Date());
		ArrayList<EmailAccount> emailAccountsList = new ArrayList(
				Arrays.asList(user.getEmailAccounts().toArray()));
		// message.setSender(((ArrayList<EmailAccount>)user.getEmailAccounts()).get(0).getEmailAccount());
		message.setSender(emailAccountsList.get(0).getEmailAccount());
		message.setReceiver(receiver);
		message.setEmail_Id(emailAccountsList.get(0).getEmail_Id());
		message.setEmail_status("SENT");
		message.setSubject(subject);
//		userDao.saveSentEmail(message);
		get.sendEMail(message, user);

	}*/

	@RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
	public void deleteMessage(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int id = Integer.parseInt(request.getParameter("id"));
		userDao.deleteMessage(id);

	}

	@RequestMapping(value = "/starredMessage", method = RequestMethod.GET)
	public void starredMessage(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int id = Integer.parseInt(request.getParameter("id"));
		userDao.starredMessage(id);

	}

	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public void myProfile(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);

	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String myHome(Locale locale, Model model, HttpServletRequest request) {
	return "home";
		
	}
	

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "logout";
		
	}
	

	/*@RequestMapping(value="/logout")
	public String showLogout(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		User user = new User();
		model.addAttribute("user", user);
		return "redirect:/";
		
	}*/
}
