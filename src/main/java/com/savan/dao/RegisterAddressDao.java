/**
 * 
 */
package com.savan.dao;

import java.util.List;

import org.json.simple.JSONObject;


/**
 * @author SAVAN
 *
 */
public interface RegisterAddressDao {

	//Register User Address
	public boolean insert(List<List<String>> addressList,int userId);
	
	//update user address
	public boolean update(List<List<String>> updateList, int userId);

	//delete user address
	public boolean deleteUserAddress(List<String> addressIds);

	//Fetching user address from the database
	public JSONObject fatchAddress(int userId);

	//deleting user's All Address
	public boolean remoeUserAddress(int userId);
	
	//delete selected address 
	public boolean remoeSelectedAddress(int addressId);
	
	//fatch user Address Count
	public boolean fatchAddressCount(int userId);

	//fatch all address ids available
	public List<String> fatchAllAddressIds(int userId);


	
}
