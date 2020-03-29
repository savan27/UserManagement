/**
 * 
 */
package com.savan.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.savan.dao.RegisterUserDao;
import com.savan.dao.impl.RegistrUserDaoImpl;
import com.savan.model.RegisterUserModel;
import com.savan.service.RegisterUserService;


/**
 * @author SAVAN
 *
 */
@MultipartConfig
public class RegisterUserServiceImpl implements RegisterUserService {

	//user Model object
	RegisterUserModel userModel = null;
	
	@Override 
	public boolean userService(HttpServletRequest request, HttpServletResponse response) {

		try {

			// Add user input to User model class
			userModel = new RegisterUserModel();

			// Check-box values
			String permission = "";
			String select[] = request.getParameterValues("permission");

			if (select != null && select.length != 0) {
				for (int i = 0; i < select.length; i++) {
					permission += select[i] + ",";
				}
			}

			// image value
			Part image = request.getPart("image_file");

			// setting values to model class
			userModel.setFirstName(request.getParameter("firstNname"));
			userModel.setLastName(request.getParameter("lastNname"));
			userModel.setPassword(request.getParameter("password"));
			userModel.setEmail(request.getParameter("email"));
			userModel.setContact(request.getParameter("contact"));
			userModel.setGender(request.getParameter("gender"));
			userModel.setHobbies(request.getParameter("hobby"));
			userModel.setPermission(permission);
			userModel.setImage(image.getInputStream());

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}

		// RegisterDaoimpl to register user
		RegisterUserDao regdao = new RegistrUserDaoImpl();
		return regdao.insert(userModel);
	}

	@Override 
	public int getIndex() {

		// RegisterDaoimpl to register user 
		RegisterUserDao regdao = new RegistrUserDaoImpl();
		return regdao.lastRow();
	}

	@Override 
	public RegisterUserModel getUserInfo(int userId) {

		// userModel instantiation
		userModel = new RegisterUserModel();

		// getting the user data
		RegisterUserDao userDao = new RegistrUserDaoImpl();

		userModel = userDao.fetchUser(userId);

		// Converting image to base64
		InputStream inputStream = userModel.getImage();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		try {
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] imageBytes = outputStream.toByteArray();
		String displayImage = Base64.getEncoder().encodeToString(imageBytes);
		userModel.setDisplayImage(displayImage);

		return userModel;
	}

	@Override
	public List<RegisterUserModel> getAllUser() {
		
		// RegisterDaoimpl to register user
		RegisterUserDao regdao = new RegistrUserDaoImpl();

		// list to store the user info
		List<RegisterUserModel> userList = new ArrayList<RegisterUserModel>();

		ResultSet rs = regdao.fetchAllUser();

		// Converting image to base64
		InputStream inputStream;
		try {
			while (rs.next()) {
				inputStream = rs.getBinaryStream(8);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				try {
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, bytesRead);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				byte[] imageBytes = outputStream.toByteArray();
				String displayImage = Base64.getEncoder().encodeToString(imageBytes);

				userList.add(new RegisterUserModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(3),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getString(10),
						displayImage, rs.getInt(11)));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return userList;
	}

	@Override 
	public int getUserId(String email) {

		// RegisterDaoimpl to register user
		RegisterUserDao regdao = new RegistrUserDaoImpl();
		return regdao.getUserId(email);
	}

	@Override 
	public boolean updateUser(HttpServletRequest request, HttpServletResponse response,int userId) {
		
		// Add user input to User model class
		RegisterUserModel userModel = new RegisterUserModel();

		try {

			// Check-box values
			String permission = "";
			String select[] = request.getParameterValues("permission");

			if (select != null && select.length != 0) {
				for (int i = 0; i < select.length; i++) {
					permission += select[i] + ",";
				}
			}

			if(request.getPart("image_file").getSize()>0) {
			
				// image value
				Part image = request.getPart("image_file");
				userModel.setImage(image.getInputStream());
			}
			else {
				String image = request.getParameter("defaultImageValue");
				
				byte[] buf = new byte[] { 0x12, 0x23 };
				buf = new sun.misc.BASE64Decoder().decodeBuffer(image);
				InputStream is = new ByteArrayInputStream(buf);
				userModel.setImage(is);
			}
			

			// setting values to model class
			userModel.setFirstName(request.getParameter("firstNname"));
			userModel.setLastName(request.getParameter("lastNname"));
			userModel.setPassword(request.getParameter("password"));
			userModel.setEmail(request.getParameter("email"));
			userModel.setContact(request.getParameter("contact"));
			userModel.setGender(request.getParameter("gender"));
			userModel.setHobbies(request.getParameter("hobby"));
			userModel.setPermission(permission);

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}

		// RegisterDaoimpl to register user
		RegisterUserDao regdao = new RegistrUserDaoImpl();
		 return regdao.editUser(userModel,userId); 
		
	}

	@Override 
	public boolean deleteUser(int userId) {
		System.out.println("1");
		// RegisterDaoimpl to register user
		RegisterUserDao regdao = new RegistrUserDaoImpl();
		return regdao.removeUser(userId);
	}

	@Override 
	public boolean userExists(String email) {
		
		// RegisterDaoimpl to register user
		RegisterUserDao regdao = new RegistrUserDaoImpl();
			if(regdao.isEmailExist(email)) {
				return true;
			}
			else {
				return false;
			}
	}

	@Override 
	public String findPassword(String name) {

		// RegisterDaoimpl to register user
		RegisterUserDao regdao = new RegistrUserDaoImpl();
		return regdao.fatchPassword(name);
		
	}

}
