/**
 * 
 */
package com.savan.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.savan.model.RegisterUserModel;

/**
 * @author SAVAN
 *
 */
public interface RegisterUserService {

		//Register User
		public boolean userService(HttpServletRequest request, HttpServletResponse response);
		
		//get last inserted User's Id 
		public int getIndex();
		
		//get user id
		public int getUserId(String email);
		
		//get user specific details
		public RegisterUserModel getUserInfo(int userId);
		
		//get all users details
		public List<RegisterUserModel> getAllUser();
		
		//update user's details
		public boolean updateUser(HttpServletRequest request, HttpServletResponse response,int userId);
		
		//Remove user's details 
		public boolean deleteUser(int userId);

		//check user exists
		public boolean userExists(String email);
		
		//Find password
		public String findPassword(String name);

	
	
}
