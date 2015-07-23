package com.daou.daouqa.moneyfighter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
import com.daou.douqa.hibernate.model.MoneyBookModel;

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
		
		Enumeration<String>	parameterEnum	= request.getParameterNames();
		String parameter					= null;
		
		while (parameterEnum.hasMoreElements()) {
			parameter	= parameterEnum.nextElement();
			LOGGER.info(parameter + "=" + request.getParameter(parameter));
		}
		
		// 파라미터 가져오기
		// date: 입력날짜
		// type: income, outcome
		// category: 항목명
		// price: 비용
		// note: 비고
		String date		= request.getParameter("date");
		String type		= request.getParameter("type");
		String category	= request.getParameter("category");
		String price	= request.getParameter("price");
		String note		= request.getParameter("note");
		//LOGGER.info(this.getClass().getResource("/aaa.txt").getPath());
		
		// Test Code Start - Start
		Session session	= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		MoneyBook moneyBook	= new MoneyBook();
		moneyBook.saveMoneybook(date, type, category, price, note);
		moneyBook.listMoneybook();
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public Integer saveMoneybook(String date, String type, String category, String price, String note) {
		Session session	= HibernateUtil.getSessionFactory().openSession();
		//System.out.println(session.isConnected());
		//System.out.println(session.isOpen());
		//session.beginTransaction();
		Integer recordId		= null;
		Transaction transaction	= null;
		try {
			transaction					= session.beginTransaction();
			MoneyBookModel moneyBook	= new MoneyBookModel();
			moneyBook.setDate(date);
			moneyBook.setType(type);
			moneyBook.setCategory(category);
			moneyBook.setPrice(Integer.parseInt(price));
			moneyBook.setNote(note);
			recordId	= (Integer) session.save(moneyBook);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return recordId;
		
	}
	
	public void listMoneybook() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<MoneyBookModel> moneyBookList = session.createQuery("from MoneyBookModel").list();
			for (Iterator<MoneyBookModel> iterator = moneyBookList.iterator(); iterator.hasNext();) {
				MoneyBookModel moneyBook = (MoneyBookModel) iterator.next();
				System.out.printf("id=%d, date=%s, type=%s, category=%s, price=%d, note=%s\n", 
						moneyBook.getRecordId(),
						moneyBook.getDate(),
						moneyBook.getType(),
						moneyBook.getCategory(),
						moneyBook.getPrice(),
						moneyBook.getNote());
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
