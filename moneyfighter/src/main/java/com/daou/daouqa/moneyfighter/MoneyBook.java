package com.daou.daouqa.moneyfighter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.daou.daouqa.hibernate.util.HibernateUtil;
import com.daou.douqa.hibernate.model.Contact;

/**
 * Servlet implementation class MoneyBook
 */
@WebServlet("/MoneyBook")
public class MoneyBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER	= Logger.getLogger(MoneyBook.class.getName());
	private static final String DEFAULT_ENCODING	= "UTF-8";
	private static final String CONTENTTYPE_JSON	= "application/json;charset=UTF-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoneyBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(DEFAULT_ENCODING);
		response.setContentType(CONTENTTYPE_JSON);
		PrintWriter pw	= response.getWriter();
		
		//LOGGER.info(this.getClass().getResource("/aaa.txt").getPath());
		
		// Test Code Start - Start
		System.out.println("Maven + Hibernate + HSQL");
		Session session	= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		MoneyBook moneyBook	= new MoneyBook();
		moneyBook.saveContact("aaa");
		moneyBook.saveContact("bbb");
		moneyBook.listContact();
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public Integer saveContact(String contactName) {
		Session session	= HibernateUtil.getSessionFactory().openSession();
		System.out.println(session.isConnected());
		System.out.println(session.isOpen());
		//session.beginTransaction();
		Integer contactId		= null;
		Transaction transaction	= null;
		try {
			transaction		= session.beginTransaction();
			Contact contact	= new Contact();
			contact.setName(contactName);
			contactId	= (Integer) session.save(contact);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return contactId;
		
	}
	
	public void listContact() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Contact> contactList = session.createQuery("from Contact").list();
			for (Iterator<Contact> iterator = contactList.iterator(); iterator.hasNext();) {
				Contact contact = (Contact) iterator.next();
				System.out.printf("id=%d, name=%s\n", contact.getContactId(), contact.getName());
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
