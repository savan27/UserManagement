package com.savan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.savan.dao.RegisterUserDao;
import com.savan.dao.impl.RegistrUserDaoImpl;
/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//Retrieving inputs credential 
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		
		// Validate the user
		RegisterUserDao  UserDao = new RegistrUserDaoImpl();
		
		if(!uname.isEmpty() && !password.isEmpty() && uname != null && password != null) {
			boolean valid = UserDao.validate(uname, password);

			if (valid){
				chain.doFilter(request, response);
			}
			else {
				request.setAttribute("loginErr", "*Invalid credentials");

				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}
		else {
			request.setAttribute("loginErr", "*Invalid credentials");
			
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
