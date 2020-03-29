/**
 * 
 */
package com.savan.service;

import java.util.List;

import org.json.simple.JSONObject;

/**
 * @author SAVAN
 *
 */
public interface RegisterAddressService {

	// Add User Address
	public boolean insertUserAddress(List<List<String>> insertList, int userId);
	
	//update user's Addresses
	public boolean updateUserAddress(List<List<String>> updateList, int userId);

	//delete user's Addresses
	public boolean deleteUserAddress(List<String> addressIds);

	//get user address
	public JSONObject getAddressInfo(int userId);
	
	//delete user's All Address
	public boolean deleteUserAddress(int userId);
	
	//delete selected User address
	public boolean deleteSelectedAddress(int addressId);
	
	//get number of address count
	public boolean getAddressCount(int userId);

	//get all address Id's available
	public List<String> getAllAddressIds(int userId);


}
