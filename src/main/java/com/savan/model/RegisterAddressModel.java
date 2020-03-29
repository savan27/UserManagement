/**
 * 
 */
package com.savan.model;

/**
 * @author SAVAN
 *
 */
public class RegisterAddressModel {

	private String[] addressId;
	private String[] address1;
	private String[] address2;
	private String[] city;
	private String[] state;
	private String[] country;
	private String[] zipcode;
	private int userId;

	/**
	 * @return the addressId
	 */
	public String[] getAddressId() {
		return addressId;
	}
	
	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(String[] addressId) {
		this.addressId = addressId;
	}
	
	/**
	 * Default constructor
	 */
	public RegisterAddressModel() {
	
	}

	/**
	 * @param address1
	 * @param address2
	 * @param city
	 * @param state
	 * @param country
	 * @param zipcode
	 * @param userId
	 */
	/*
	 * public RegisterAddressModel(String address1, String address2, String city,
	 * String state, String country, String zipcode, int userId) { this.address1 =
	 * address1; this.address2 = address2; this.city = city; this.state = state;
	 * this.country = country; this.zipcode = zipcode; this.userId = userId; }
	 */

	/**
	 * @return the zipcode
	 */
	public String[] getZipcode() {
		return zipcode;
	}

	/**
	 * @param strings the zipcode to set
	 */
	public void setZipcode(String[] strings) {
		this.zipcode = strings;
	}

	/**
	 * @return the address1
	 */
	public String[] getAddress1() {
		return address1;
	}

	/**
	 * @param strings the address1 to set
	 */
	public void setAddress1(String[] strings) {
		this.address1 = strings;
	}

	/**
	 * @return the address2
	 */
	public String[] getAddress2() {
		return address2;
	}

	/**
	 * @param strings the address2 to set
	 */
	public void setAddress2(String[] strings) {
		this.address2 = strings;
	}

	/**
	 * @return the city
	 */
	public String[] getCity() {
		return city;
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
	 * @param strings the city to set
	 */
	public void setCity(String[] strings) {
		this.city = strings;
	}

	/**
	 * @return the state
	 */
	public String[] getState() {
		return state;
	}

	/**
	 * @param strings the state to set
	 */
	public void setState(String[] strings) {
		this.state = strings;
	}

	/**
	 * @return the country
	 */
	public String[] getCountry() {
		return country;
	}

	/**
	 * @param strings the country to set
	 */
	public void setCountry(String[] strings) {
		this.country = strings;
	}

}
