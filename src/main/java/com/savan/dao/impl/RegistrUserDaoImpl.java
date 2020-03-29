/**
 * 
 */
package com.savan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.savan.config.DBConnection;
import com.savan.dao.RegisterUserDao;
import com.savan.model.RegisterUserModel;

/**
 * @author SAVAN
 *
 */
public class RegistrUserDaoImpl implements RegisterUserDao {

	// Connection object
	Connection con = null;

	// User model class object
	RegisterUserModel userModel = null;

	@Override
	public boolean insert(RegisterUserModel userModel) {

		// Insert Query
		String sql = "INSERT INTO user_register (first_name,last_name,password,email,contact,gender,profile_photo,permission,hobbies,roll_id) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, userModel.getFirstName());
			ps.setString(2, userModel.getLastName());
			ps.setString(3, userModel.getPassword());
			ps.setString(4, userModel.getEmail());
			ps.setString(5, userModel.getContact());
			ps.setString(6, userModel.getGender());
			ps.setBlob(7, userModel.getImage());
			ps.setString(8, userModel.getPermission());
			ps.setString(9, userModel.getHobbies());
			ps.setInt(10, 2);

			int ans = ps.executeUpdate();
			
			if (ans > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean validate(String userName, String password) {

		// Validate User
		String sql = "SELECT password,email FROM user_register WHERE email=? and password=?";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public int lastRow() {

		// Get last Row Id
		String sql = "select Max(user_id) from user_register";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public boolean isEmailExist(String email) {

		// Validate User
		String sql = "SELECT email FROM user_register WHERE email=?";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public int getUserId(String email) {

		// Validate User
		String sql = "SELECT user_id FROM user_register WHERE email=?";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}

	@Override
	public RegisterUserModel fetchUser(int userId) {

		// Validate User
		String sql = "SELECT * FROM user_register WHERE user_id=?";

		try {
			
			con = DBConnection.getConnection();
			userModel = new RegisterUserModel();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				userModel.setUserRId(rs.getInt(1));
				userModel.setFirstName(rs.getString(2));
				userModel.setLastName(rs.getString(3));
				userModel.setPassword(rs.getString(4));
				userModel.setEmail(rs.getString(5));
				userModel.setContact(rs.getString(6));
				userModel.setGender(rs.getString(7));
				userModel.setImage(rs.getBinaryStream(8));
				userModel.setPermission(rs.getString(9));
				userModel.setHobbies(rs.getString(10));
				userModel.setUserRole(rs.getInt(11));

			}
			return userModel;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ResultSet fetchAllUser() {

		// Validate User
		String sql = "SELECT * FROM user_register";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean editUser(RegisterUserModel userModel,int userId) {

		//Update Query
		String sql = "UPDATE user_register SET first_name=?,last_name=?,password=?,email=?,contact=?,gender=?,profile_photo=?,permission=?,hobbies=?,roll_id=? WHERE user_id=?";

		try {

			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, userModel.getFirstName());
			ps.setString(2, userModel.getLastName());
			ps.setString(3, userModel.getPassword());
			ps.setString(4, userModel.getEmail());
			ps.setString(5, userModel.getContact());
			ps.setString(6, userModel.getGender());
			ps.setBlob(7, userModel.getImage());
			ps.setString(8, userModel.getPermission());
			ps.setString(9, userModel.getHobbies());
			ps.setInt(10, 2);
			ps.setInt(11, userId);

			int ans = ps.executeUpdate();
			
			if (ans > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeUser(int userId) {
		
		//Delete Query
		String sql = "DELETE FROM user_register WHERE user_id=?";
		
		try {

			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			int ans = ps.executeUpdate();
			System.out.println("ans :"+ans);
			if (ans > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public String fatchPassword(String name) {

		// Validate User
		String sql = "SELECT password FROM user_register WHERE email=?";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String password = rs.getString("password"); 
				return password;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<String> getUser(String userName) {

		// address count query
		String sql = "SELECT * FROM user_register WHERE email=?";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userName);

			ResultSet rs = ps.executeQuery();

			// List of user Id
			List<String> user = new ArrayList<String>();

			while (rs.next()) {
				
				user.add(Integer.toString(rs.getInt(1)));
				user.add(rs.getString(2));
				user.add(rs.getString(3));
				user.add(rs.getString(4));
				user.add(rs.getString(5));
				user.add(rs.getString(6));
				user.add(rs.getString(7));
				user.add(rs.getString(9));
				user.add(rs.getString(10));
				user.add(rs.getString(11));

			}

			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
		
	}

}
