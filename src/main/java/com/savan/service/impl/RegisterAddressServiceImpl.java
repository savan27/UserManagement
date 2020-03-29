/**
 * 
 */
package com.savan.service.impl;

import java.util.List;


import org.json.simple.JSONObject;

import com.savan.service.RegisterAddressService;
import com.savan.dao.RegisterAddressDao;
import com.savan.dao.impl.RegistrAddressDaoImpl;

/**
 * @author SAVAN
 *
 */
public class RegisterAddressServiceImpl implements RegisterAddressService {

	@Override 
	public boolean insertUserAddress(List<List<String>> insertList, int userId) {
		
		/*
		 * //Add user input to Address model class RegisterAddressModel addressModel =
		 * new RegisterAddressModel();
		 * 
		 * addressModel.setAddress1(request.getParameterValues("home"));
		 * addressModel.setAddress2(request.getParameterValues("leandmark"));
		 * addressModel.setCity(request.getParameterValues("City"));
		 * addressModel.setState(request.getParameterValues("State"));
		 * addressModel.setCountry(request.getParameterValues("Country"));
		 * addressModel.setZipcode(request.getParameterValues("ZipCode"));
		 * 
		 * // get the data from address model String[] home =
		 * addressModel.getAddress1(); String[] landmark = addressModel.getAddress2();
		 * String[] city = addressModel.getCity(); String[] state =
		 * addressModel.getState(); String[] country = addressModel.getCountry();
		 * String[] zipcode = addressModel.getZipcode();
		 * 
		 * // list to store user addresslist List<List<String>> addressList = new
		 * ArrayList<List<String>>();
		 * 
		 * for (int count = 0; count < home.length; count++) {
		 * 
		 * // List to store address depratly List<String> address = new
		 * ArrayList<String>();
		 * 
		 * address.add(home[count]); address.add(landmark[count]);
		 * address.add(city[count]); address.add(state[count]);
		 * address.add(country[count]); address.add(zipcode[count]);
		 * 
		 * //appaend seprate addresses to addresslist addressList.add(address); }
		 */
		
		//RegisterAddressDaoImpl to register user Address
		RegisterAddressDao addressDao = new RegistrAddressDaoImpl();
		return addressDao.insert(insertList,userId);
	}

	@Override 
	public JSONObject getAddressInfo(int userId) {
		//getting the user address
		RegisterAddressDao addressdao = new RegistrAddressDaoImpl();
		return addressdao.fatchAddress(userId);
	}

	@Override 
	public boolean updateUserAddress(List<List<String>> updateList, int userId) {
		
		/*
		 * // Add user input to Address model class RegisterAddressModel addressModel =
		 * new RegisterAddressModel();
		 * 
		 * addressModel.setAddressId(request.getParameterValues("addressId"));
		 * addressModel.setAddress1(request.getParameterValues("home"));
		 * addressModel.setAddress2(request.getParameterValues("leandmark"));
		 * addressModel.setCity(request.getParameterValues("City"));
		 * addressModel.setState(request.getParameterValues("State"));
		 * addressModel.setCountry(request.getParameterValues("Country"));
		 * addressModel.setZipcode(request.getParameterValues("ZipCode"));
		 * 
		 * // get the data from address model String[] addressId =
		 * addressModel.getAddressId(); String[] home = addressModel.getAddress1();
		 * String[] landmark = addressModel.getAddress2(); String[] city =
		 * addressModel.getCity(); String[] state = addressModel.getState(); String[]
		 * country = addressModel.getCountry(); String[] zipcode =
		 * addressModel.getZipcode();
		 * 
		 * // list to store user addresslist List<List<String>> addressList = new
		 * ArrayList<List<String>>();
		 * 
		 * for (int count = 0; count < home.length; count++) {
		 * 
		 * // List to store address depratly List<String> address = new
		 * ArrayList<String>();
		 * 
		 * address.add(addressId[count]); address.add(home[count]);
		 * address.add(landmark[count]); address.add(city[count]);
		 * address.add(state[count]); address.add(country[count]);
		 * address.add(zipcode[count]);
		 * 
		 * // appaend seprate addresses to addresslist addressList.add(address); }
		 */
		
		// RegisterAddressDaoImpl to register user Address
		RegisterAddressDao addressDao = new RegistrAddressDaoImpl();
		return addressDao.update(updateList, userId);
	}

	@Override 
	public boolean deleteUserAddress(int userId) {

		// RegisterAddressDaoImpl to register user Address
		RegisterAddressDao addressDao = new RegistrAddressDaoImpl();
		return addressDao.remoeUserAddress(userId);
		
	}

	@Override
	public boolean deleteSelectedAddress(int addressId) {

		// RegisterAddressDaoImpl to register user Address
		RegisterAddressDao addressDao = new RegistrAddressDaoImpl();
		return addressDao.remoeSelectedAddress(addressId);
	}

	@Override 
	public boolean getAddressCount(int userId) {

		// RegisterAddressDaoImpl to register user Address
		RegisterAddressDao addressDao = new RegistrAddressDaoImpl();
		return addressDao.fatchAddressCount(userId);
	}

	@Override
	public List<String> getAllAddressIds(int userId) {
		
		// RegisterAddressDaoImpl to register user Address
		RegisterAddressDao addressDao = new RegistrAddressDaoImpl();
		return addressDao.fatchAllAddressIds(userId);
	}

	@Override
	public boolean deleteUserAddress(List<String> addressIds) {
		
		// RegisterAddressDaoImpl to register user Address
		RegisterAddressDao addressDao = new RegistrAddressDaoImpl();
		return addressDao.deleteUserAddress(addressIds);
	}

	
}
