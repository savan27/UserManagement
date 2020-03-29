package com.savan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.savan.model.RegisterUserModel;
import com.savan.service.RegisterAddressService;
import com.savan.service.RegisterUserService;
import com.savan.service.impl.RegisterAddressServiceImpl;
import com.savan.service.impl.RegisterUserServiceImpl;
/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session creation
		HttpSession session = request.getSession();
		
		//userModel instantiation
		RegisterUserModel userModel = new RegisterUserModel();
		
		//userService instantiation
		RegisterUserService userService = new RegisterUserServiceImpl();
		
		//addresService instantiation
		RegisterAddressService addressService = new RegisterAddressServiceImpl();
		
		//getting user id and set it to model class 
		userModel.setUserId(userService.getUserId(request.getParameter("uname")));

		//getting user Id from user model
		int userId = userModel.getUserId();

		//get user info from database
		userModel = userService.getUserInfo(userId);
		
		//getting user model from model
		int userRole = userModel.getUserRole();
				
		if(userRole == 2) {
			
			// get user Address from database 
			JSONObject userAddress = addressService.getAddressInfo(userId);

			//setting values to session 
			session.setAttribute("role", "user");
			session.setAttribute("addressData", userAddress);
			session.setAttribute("userData", userModel);
						
			//set max session time to 30 Minutes
			session.setMaxInactiveInterval(30 * 60);
						
			//redirect to result.jsp
			response.sendRedirect("result.jsp");
		}
		else {

			//getting all user info
			List<RegisterUserModel> userList = userService.getAllUser();
			
			//setting values to session 
			session.setAttribute("role", "admin");
			session.setAttribute("userList", userList);
			
			//set max session time till brouser closed
			session.setMaxInactiveInterval(30 * 60);
			
			//redirect to result.jsp
			response.sendRedirect("result.jsp");
			
		}
		
	}

}
