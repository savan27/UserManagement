/**
 * 
 */
package com.savan.dao;

import java.sql.ResultSet;
import java.util.List;

import com.savan.model.RegisterUserModel;

/**
 * @author SAVAN
 *
 */
public interface RegisterUserDao {

	//Register User 
	public boolean insert(RegisterUserModel UserModel);
	
	//validate logging user
	public boolean validate(String userName, String password);
	
	//get last inserted User's Id 
	public int lastRow();
	
	//check User exist or not
	public boolean isEmailExist(String email);
	
	//get Existing User's Unick Id
	public int getUserId(String email);
	
	//get User Specific Details
	public RegisterUserModel fetchUser(int userId);
	
	// get All Users Details
	public ResultSet fetchAllUser();
	
	//update User Details
	public boolean editUser(RegisterUserModel userModel,int userId);

	//Remove User Details 
	public boolean removeUser(int userId);

	//Fatch Password 
	public String fatchPassword(String name);

	//get user
	public List<String> getUser(String userName);

}
