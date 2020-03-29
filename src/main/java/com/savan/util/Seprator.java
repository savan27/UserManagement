/**
 * 
 */
package com.savan.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.savan.model.RegisterAddressModel;

/**
 * @author SAVAN
 *
 */
public class Seprator {

	public List<List<String>> addressList(HttpServletRequest request, HttpServletResponse response) {
		
		// Add user input to Address model class
		RegisterAddressModel addressModel = new RegisterAddressModel();

		addressModel.setAddressId(request.getParameterValues("addressId"));
		addressModel.setAddress1(request.getParameterValues("home"));
		addressModel.setAddress2(request.getParameterValues("leandmark"));
		addressModel.setCity(request.getParameterValues("City"));
		addressModel.setState(request.getParameterValues("State"));
		addressModel.setCountry(request.getParameterValues("Country"));
		addressModel.setZipcode(request.getParameterValues("ZipCode"));

		// get the data from address model
		String[] addressId = addressModel.getAddressId();
		String[] home = addressModel.getAddress1();
		String[] landmark = addressModel.getAddress2();
		String[] city = addressModel.getCity();
		String[] state = addressModel.getState();
		String[] country = addressModel.getCountry();
		String[] zipcode = addressModel.getZipcode();

		// list to store user addresslist
		List<List<String>> addressList = new ArrayList<List<String>>();

		for (int count = 0; count < home.length; count++) {

			// List to store address depratly
			List<String> address = new ArrayList<String>();

			address.add(addressId[count]);
			address.add(home[count]);
			address.add(landmark[count]);
			address.add(city[count]);
			address.add(state[count]);
			address.add(country[count]);
			address.add(zipcode[count]);

			// appaend seprate addresses to addresslist
			addressList.add(address);
		}
		
		return addressList;
		
	}

	/*
	 * //devide operation Specific Data public boolean devideData(List<List<String>>
	 * addressList, List<String> addressIds) {
	 * 
	 * //Update List List<List<String>> update = new ArrayList<List<String>>();
	 * 
	 * //Insert List List<List<String>> insert = new ArrayList<List<String>>();
	 * 
	 * for (int i = 0; i < addressList.size(); i++) {
	 * 
	 * if (addressIds.contains(addressList.get(i).get(0))) {
	 * 
	 * update.add(addressList.get(i));
	 * 
	 * addressIds.remove(addressList.get(i).get(0)); } if
	 * ("newAdd".equals(addressList.get(i).get(0))) {
	 * 
	 * insert.add(addressList.get(i)); } }
	 * 
	 * System.out.println("inside update :"+update);
	 * System.out.println("inside insert :"+insert);
	 * System.out.println("inside delete :"+addressIds);
	 * 
	 * return false; }
	 */
	
}
