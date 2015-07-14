package com.daou.daouqa.moneyfighter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class TestCase
 */
public class Sample extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER	= Logger.getLogger(Sample.class.getName());
	private static final String DEFAULT_ENCODING	= "UTF-8";
	private static final String CONTENTTYPE_JSON	= "application/json;charset=UTF-8";
	static final String DB_URL	= "jdbc:mysql://175.115.94.150:3306/daoutcm";
	static final String DB_USER	= "root";
	static final String DB_PASS	= "secret";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sample() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(DEFAULT_ENCODING);
		response.setContentType(CONTENTTYPE_JSON);
		
		System.out.println("aaa");
		String action		= request.getParameter("action");
		log("action=" + action);
		
		Enumeration<String>	parameterEnum	= request.getParameterNames();
		String parameter					= null;
		
		while (parameterEnum.hasMoreElements()) {
			parameter	= parameterEnum.nextElement();
			log(parameter + "=" + request.getParameter(parameter));
		}
		
		System.out.println("aaa");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(DEFAULT_ENCODING);
		response.setContentType(CONTENTTYPE_JSON);
		
		String action		= request.getParameter("action");
		log("action=" + action);
		
		Enumeration<String>	parameterEnum	= request.getParameterNames();
		String parameter					= null;
		
		while (parameterEnum.hasMoreElements()) {
			parameter	= parameterEnum.nextElement();
			log(parameter + "=" + request.getParameter(parameter));
		}
		
	}

}
