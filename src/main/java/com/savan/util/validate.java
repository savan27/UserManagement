/**
 * 
 */
package com.savan.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.savan.service.RegisterUserService;
import com.savan.service.impl.RegisterUserServiceImpl;


/**
 * @author SAVAN
 *
 */
public class validate {

	public String validateUser(HttpServletRequest request, HttpServletResponse response) {

		//seccess Massage
		String vlidate = "success";
		
		if (isContainWhitespace(request.getParameter("firstNname")) || isContainDigit(request.getParameter("firstNname")) || isContainSpecialCharacter(request.getParameter("firstNname")) || (request.getParameter("firstNname").length() < 2) || (request.getParameter("firstNname").length() > 10)) {
			String error = "*First name should not contain Digit,Special Characters,Space Between Characters and Length should be 2 to 10 character..!!";
			return error;
		}
		if (isContainWhitespace(request.getParameter("lastNname")) || isContainDigit(request.getParameter("lastNname")) || isContainSpecialCharacter(request.getParameter("lastNname")) || (request.getParameter("lastNname").length() < 2) || (request.getParameter("lastNname").length() > 10)) {
			String error = "*Last name should not contain Digit,Special Characters,Space Between Characters and Length should be 2 to 10 character..!!";
			return error;
		}
		if (request.getParameter("password") == null || request.getParameter("password").equals("") || isContainWhitespace(request.getParameter("password")) || (request.getParameter("password").length() < 8) || (request.getParameter("password").length() > 12)) {
			String error = "*Password Length should be 8 to 12 character and Space Between Characters not allowed..!!";
			return error;
		}
		if (!request.getParameter("password").equals(request.getParameter("Cpassword")) || request.getParameter("Cpassword") == null || request.getParameter("Cpassword").equals("")) {
			String error = "*Password and Confirm Password dosen't match..!!";
			return error;
		}
		if (emailExist(request.getParameter("email"),Integer.parseInt(request.getParameter("uid")))) {
			String error = "*Email Exists Please Try Another One..!!";
			return error;
		}
		if (isValidEmail(request.getParameter("email"))) {
			String error = "*Email should be in the 'xxx@xxx.xx' Format..!!";
			return error;
		}
		if (isContainCharacter(request.getParameter("contact")) || isContainSpecialCharacter(request.getParameter("contact")) || isContainWhitespace(request.getParameter("contact"))) {
			String error = "*Contact Number should not contain Characters,Special Characters,Space Between Characters and Length must be 10 Digits..!!";
			return error;
		}
	
		return vlidate;
	}
	
	public String validateAddress(HttpServletRequest request, HttpServletResponse response) {
		
		//seccess Massage
		String vlidate = "success";
		
		if (request.getParameter("home") == null || request.getParameter("home").equals("") || (request.getParameter("home").length() < 3) || (request.getParameter("home").length() > 30)) {
			String error = "*Addressline1 should not be Empty and Length should be 5 to 30 character..!!";
			return error;
		}
		if (request.getParameter("leandmark") == null || request.getParameter("leandmark").equals("") || (request.getParameter("leandmark").length() < 3) || (request.getParameter("leandmark").length() > 30)) {
			String error = "*Addressline2 should not be Empty and Length should be 5 to 30 character..!!";
			return error;
		}
		if (isContainWhitespace(request.getParameter("City")) || isContainDigit(request.getParameter("City")) || isContainSpecialCharacter(request.getParameter("City")) || (request.getParameter("City").length() < 3) || (request.getParameter("City").length() > 10)) {
			String error = "*City name should not contain Digit,Special Characters,Space Between Characters and Length should be 3 to 10 character..!!";
			return error;
		}
		if (isContainWhitespace(request.getParameter("State")) || isContainDigit(request.getParameter("State")) || isContainSpecialCharacter(request.getParameter("State")) || (request.getParameter("State").length() < 3) || (request.getParameter("State").length() > 10)) {
			String error = "*State name should not contain Digit,Special Characters,Space Between Characters and Length should be 3 to 10 character..!!";
			return error;
		}
		if (isContainWhitespace(request.getParameter("Country")) || isContainDigit(request.getParameter("Country")) || isContainSpecialCharacter(request.getParameter("Country")) || (request.getParameter("Country").length() < 4) || (request.getParameter("Country").length() > 10)) {
			String error = "*Country name should not contain Digit,Special Characters,Space Between Characters and Length should be 4 to 10 character..!!";
			return error;
		}
		if (isContainCharacter(request.getParameter("ZipCode")) || isContainSpecialCharacter(request.getParameter("ZipCode")) || isContainWhitespace(request.getParameter("ZipCode")) || (request.getParameter("ZipCode").length() < 5) || (request.getParameter("ZipCode").length() > 10)) {
			String error = "*ZipCode should not contain Characters,Special Characters,Space Between Characters and Length must be 5 to 10 Digits..!!";
			return error;
		}
		
		return vlidate;
	}
	
	public String validateData(HttpServletRequest request, HttpServletResponse response) {

		//seccess Massage
		String vlidate = "success";
		
		if (request.getParameter("operation").contentEquals("Update")) {
			if (emailExist(request.getParameter("email"),Integer.parseInt(request.getParameter("uid")))) {
				String error = "*Email Exists Please Try Another One..!!";
				return error;
			}
		}
		if (userAvail(request.getParameter("email"),request.getParameter("operation") )) {
			String error = "*Email Exists Please Try Another One..!!";
			return error;
		}
		if (isValidEmail(request.getParameter("email"))) {
			String error = "*Email should be in the 'xxx@xxx.xx' Format..!!";
			return error;
		}
		
		return vlidate;
	}
	
	private boolean isContainWhitespace(String name) {
		
		// check if empty

		if (name == null || name.equals("")) {
			return true;
		}

		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(name.trim());

		if (matcher.find()) {
			return true;
		} 
		
		return false;
	}

	private boolean isContainDigit(String name) {

		// check if empty
		if (name == null || name.equals("")) {
			return true;
		}
		
		Pattern pattern = Pattern.compile(".*\\d.*");
		Matcher matcher = pattern.matcher(name.trim());

		if (matcher.find()) {
			return true;
		} 
		
		return false;
	}

	private boolean isContainSpecialCharacter(String name) {
		
		// check if empty
		if (name == null || name.equals("")) {
			return true;
		}
						
		return false;
	}

	private boolean isContainCharacter(String name) {
		
		// check if empty
		if (name == null || name.equals("")) {
			return true;
		}
		
		Pattern pattern = Pattern.compile("[a-zA-z]");
		Matcher matcher = pattern.matcher(name.trim());

		if (matcher.find()) {
			return true;
		} 
		
		return false;
	}

	private boolean isValidEmail(String name) {
		
		// check if empty
		if (name == null || name.equals("")) {
			return true;
		}
 
		Pattern pattern = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(name.trim());

		if (!matcher.find()) {
			return true;
		}
		return false;
	}

	
	private boolean emailExist(String email,int userId) { 
		
		// User Register object
		RegisterUserService userService = new RegisterUserServiceImpl();

			//getting userId related with email
			int user = userService.getUserId(email);

			//check if existing user is updating
			if (userId == user) {
				return false;
			}
			else {
				return true;
			}
	}
	
	private boolean userAvail(String email,String operation) { 

		// User Register object
		RegisterUserService userService = new RegisterUserServiceImpl();
		
			if (userService.userExists(email) && (!operation.equals("Update")) ) {
				return true;
			}
		
		return false;
	}
}
