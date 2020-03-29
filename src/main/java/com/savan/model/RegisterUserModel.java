/**
 * 
 */
package com.savan.model;


import java.io.InputStream;

/**
 * @author SAVAN
 *
 */
public class RegisterUserModel {

	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String contact;
	private String gender;
	private String permission;
	private String hobbies;
	
	//User's Unick ID
	private int userRId;

	//image to display from database
	private String displayImage;
	
	//for image file 
	private InputStream image;
	
	//userId from Update request
 	private int userId;
	
	//user Role
	private int userRole;
	

	/**
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param email
	 * @param contact
	 * @param gender
	 * @param permission
	 * @param hobbies
	 * @param image
	 * @param userRole
	 */
	public RegisterUserModel(int userRId,String firstName, String lastName, String password, String email, String contact,
			String gender, String permission, String hobbies, String displayImage,int userRole) {
		this.userRId = userRId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.contact = contact;
		this.gender = gender;
		this.permission = permission;
		this.hobbies = hobbies;
		this.displayImage = displayImage;
		this.userRole = userRole;
	}

	/**
	 * @return the userRId
	 */
	public int getUserRId() {
		return userRId;
	}

	/**
	 * @param userRId the userRId to set
	 */
	public void setUserRId(int userRId) {
		this.userRId = userRId;
	}


	/**
	 * Default constructor
	 */
	public RegisterUserModel() {

	}


	/**
	 * @return the displayImage
	 */
	public String getDisplayImage() {
		return displayImage;
	}

	/**
	 * @param displayImage the displayImage to set
	 */
	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}
	
	/**
	 * @return the userRole
	 */
	public int getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}



	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

		
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * @return the hobbies
	 */
	public String getHobbies() {
		return hobbies;
	}

	/**
	 * @param hobbies the hobbies to set
	 */
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	/**
	 * @return the image
	 */
	public InputStream getImage() {
		return image;
	}

	/**
	 * @param inputStream the image to set
	 */
	public void setImage(InputStream inputStream) {
		this.image = inputStream;
	}
	
}
