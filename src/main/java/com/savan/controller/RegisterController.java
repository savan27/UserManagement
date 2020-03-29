package com.savan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import com.savan.util.Seprator;
import com.savan.util.validate;

/**
 * Servlet implementation class RegisterController
 */
@MultipartConfig
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//session creation
		HttpSession session = request.getSession();

		// userModel instantiation
		RegisterUserModel userModel = new RegisterUserModel();

		// userService instantiation
		RegisterUserService userService = new RegisterUserServiceImpl();

		// addresService instantiation
		RegisterAddressService addressService = new RegisterAddressServiceImpl();

		if (request.getParameter("operaton").equals("Update")) {

			//getting user id for identify user
			int userId = Integer.parseInt(request.getParameter("id"));

			// get user info from database
			userModel = userService.getUserInfo(userId);

			// get user Address from database
			JSONObject userAddressModel = addressService.getAddressInfo(userId);
			
			//setting data into session 
			session.setAttribute("userData", userModel);
			session.setAttribute("addressData", userAddressModel);
			session.setAttribute("operation", request.getParameter("operaton"));
			session.setAttribute("userId", request.getParameter("id"));
			session.setAttribute("userRole", request.getParameter("role"));
			
			//redirect ot register.jsp
			response.sendRedirect("register.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)how to differentiate
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//session creation
		HttpSession session = request.getSession();
		
		// User Register object
		RegisterUserService userRegister = new RegisterUserServiceImpl();

		// redirect to the Register Address
		RegisterAddressService addressRegister = new RegisterAddressServiceImpl();
		
		//userModel instantiation
		RegisterUserModel userModel = new RegisterUserModel();
		
		//Utility calss to manage addresses
		Seprator seprate = new Seprator();
		
		//utility class to vaidation
		validate valid = new validate();
		
		/*
		 * 
		 * Update User 
		 * 
		 */
		if (request.getParameter("operation").contentEquals("Update")) {
				
				// getting userId
				int userId = Integer.parseInt(request.getParameter("uid"));
				
				//create address List From user Input 
				List<List<String>> addressList = seprate.addressList(request, response);
				
				//get all address Ids of User
				List<String> addressIds = addressRegister.getAllAddressIds(userId);
				
				//Update List
				List<List<String>> updateList = new ArrayList<List<String>>();

				//Insert List
				List<List<String>> insertList = new ArrayList<List<String>>();
				
				//validating user
				String isvalid = valid.validateData(request, response);
				
				if (isvalid != "success") {

					//add erre to session
					session.setAttribute("errMassage","email is already in use..!!");
					
					//redirect to register page
					response.sendRedirect("register.jsp");
				}
				else {
					
					//remove data from session
					session.removeAttribute("errMassage");
					
					//Updating userData
					boolean isUserUpdated = userRegister.updateUser(request, response, userId);
					
					if (isUserUpdated) {
						
						//seprate data for CRUD
						for (int i = 0; i < addressList.size(); i++) {
							
							if (addressIds.contains(addressList.get(i).get(0))) {
								
								// add address to update List
								updateList.add(addressList.get(i));
								
								// remove addressId from List
								addressIds.remove(addressList.get(i).get(0));
							}
							if ("newAdd".equals(addressList.get(i).get(0))) {
								
								// add address to Insert List
								insertList.add(addressList.get(i));
							}
						}
						
						boolean isAddressUpdated = true;
						boolean isAddressInserted = true;
						boolean isAddressDeleted = true;
						
						if (!updateList.isEmpty()) {
							
							// updating user Address Data
							isAddressUpdated = addressRegister.updateUserAddress(updateList, userId);
						}
						if (!insertList.isEmpty()) {
							
							// updating user Address Data
							isAddressInserted = addressRegister.insertUserAddress(insertList, userId);
						}
						if (!addressIds.isEmpty()) {
							
							// delete user Address Data
							isAddressDeleted = addressRegister.deleteUserAddress(addressIds);
						}
						
						//check if address update success
						if (isAddressUpdated && isAddressInserted && isAddressDeleted) {
							
							//remove data into session 
							session.removeAttribute("userData");
							session.removeAttribute("addressData");
							session.removeAttribute("operation");
							session.removeAttribute("userId");
							session.removeAttribute("userRole");
							
							//Select view to response from the update request
							if (request.getParameter("userRole").contentEquals("admin")) {
								
								//getting all user info
								List<RegisterUserModel> userList = userRegister.getAllUser();
								
								//setting values to session 
								session.setAttribute("role", "admin");
								session.setAttribute("userList", userList);
								
								//set max session time till brouser closed
								session.setMaxInactiveInterval(30 * 60);
								
								//redirect to result.jsp
								response.sendRedirect("result.jsp");
							}
							else {
								
								//get user info from database
								userModel = userRegister.getUserInfo(userId);
								
								// get user Address from database 
								JSONObject userAddress = addressRegister.getAddressInfo(userId);
								
								//setting values to session 
								session.setAttribute("role", "user");
								session.setAttribute("addressData", userAddress);
								session.setAttribute("userData", userModel);
								
								//set max session time to 30 Minutes
								session.setMaxInactiveInterval(30 * 60);
								
								//redirect to result.jsp
								response.sendRedirect("result.jsp");
							}
						} else {
							System.out.println("Address Updated fail....");
							
							// get user info from database
							userModel = userRegister.getUserInfo(userId);
							
							// get user Address from database
							JSONObject userAddressModel = addressRegister.getAddressInfo(userId);
							
							//setting data into session 
							session.setAttribute("errMassage", "User Updated fail....");
							session.setAttribute("userData", userModel);
							session.setAttribute("addressData", userAddressModel);
							session.setAttribute("operation", request.getParameter("operaton"));
							session.setAttribute("userId", userId);
							session.setAttribute("userRole", request.getParameter("userRole"));
							
							//redirect ot register.jsp
							response.sendRedirect("register.jsp");
							
						}
					} else {
						System.out.println("User Updated fail....");
						
						// get user info from database
						userModel = userRegister.getUserInfo(userId);
						
						// get user Address from database
						JSONObject userAddressModel = addressRegister.getAddressInfo(userId);
						
						//setting data into session 
						session.setAttribute("errMassage", "User Updated fail....");
						session.setAttribute("userData", userModel);
						session.setAttribute("addressData", userAddressModel);
						session.setAttribute("operation", request.getParameter("operaton"));
						session.setAttribute("userId", userId);
						session.setAttribute("userRole", request.getParameter("userRole"));
						
						//redirect ot register.jsp
						response.sendRedirect("register.jsp");
					}
				}
		 }
		
		/*
		 * 
		 * Remove User By Admin
		 * 
		 */
		else if (request.getParameter("operation").contentEquals("removeUser")) {
			
			// get userId
			int userId = Integer.parseInt(request.getParameter("userId"));

			// deleting user
			boolean isuserDeleted = userRegister.deleteUser(userId);

			if (isuserDeleted) {
				response.getWriter().write("User Deletion Success full.........");
			} else {
				response.getWriter().write("Fail to Delelte User.........");
			}
		}
		
		/*
		 * 
		 * Forgot Password
		 * 
		 */
		else if (request.getParameter("operation").contentEquals("forgotPassword")) {
			
			//passeord fatched from the database
			String password = userRegister.findPassword(request.getParameter("userEmail"));
			
			if(password != null) {
				response.getWriter().write("Your Password Is : "+password);
			}
			else {
				response.getWriter().write("Please Enter Valide Enail Id..!!");
			}
			
		}
		
		/*
		 * 
		 * Redirecting User aftr cancle 
		 * 
		 */
		else if (request.getParameter("operation").contentEquals("cancel")) {
				
				// remove errormassage 
				session.removeAttribute("errMassage");
				
				//remove form values 
				session.removeAttribute("firstName"); 
				session.removeAttribute("lastName");
				session.removeAttribute("email");
				session.removeAttribute("contact");
				session.removeAttribute("asd");
				
				//redirect to login
				response.sendRedirect("login.jsp");
		}
		
		
		/*
		 * 
		 * Register User 
		 * 
		 */
		 else{

			//create address List From user Input 
			List<List<String>> addressList = seprate.addressList(request, response);
			 
			//validating user
			String isvalid = valid.validateData(request, response);
			
			if (isvalid != "success") {
				
				//add erre to session
				session.setAttribute("errMassage","email is already in use..!!");
				
				//set form values 
				session.setAttribute("firstName", request.getParameter("firstNname")); 
				session.setAttribute("lastName", request.getParameter("lastNname"));
				session.setAttribute("email", request.getParameter("email"));
				session.setAttribute("contact", request.getParameter("contact"));
				
				//redirect to register page
				response.sendRedirect("register.jsp");
			}
			else {

				// add user to database
					boolean isregister = userRegister.userService(request, response);
					
					// if registered then login
					if (isregister) {
						
						// get Id of last inserted row
						int lastId = userRegister.getIndex();
						
						// updating user Address Data
						boolean isAddressInserted = addressRegister.insertUserAddress(addressList, lastId);
						
						if (isAddressInserted) {
							
							// Success Massage
							session.setAttribute("loginMsg", "*Login with your e-mail and password");
							
							// redirecting user to login
							response.sendRedirect("login.jsp");
							
						} else {
							
							// deleting user
							boolean isuderDeleted = userRegister.deleteUser(lastId);
							
							if (isuderDeleted) {
								
								// Failure Massage
								session.setAttribute("errMassage", "Process Fail Please Try Again....");
								
								// writing the error massage of validation
								response.sendRedirect("register.jsp");
							}
						}
					} else {
						
						// Failure Massage
						session.setAttribute("errMassage", "Process Fail Please Try Again....");
						
						// writing the error massage of validation
						response.sendRedirect("register.jsp");
					}
				}
			}
		}
	}

