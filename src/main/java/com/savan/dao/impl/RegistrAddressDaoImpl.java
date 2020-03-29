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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.savan.config.DBConnection;
import com.savan.dao.RegisterAddressDao;
import com.savan.model.RegisterAddressModel;

/**
 * @author inex2
 *
 */
public class RegistrAddressDaoImpl implements RegisterAddressDao {

	// Connection object
	Connection con = null;

	// Address model class object
	RegisterAddressModel addressModel = null;

	@Override
	public boolean insert(List<List<String>> addressList, int userId) {

		// Insert Query
		String sql = "INSERT INTO user_address (line_1,line_2,city,state,country,zipcode,user_id) VALUES(?,?,?,?,?,?,?);";

		try {

			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			// variable to store query result
			int ans = 0;

			// initlizeation of index
			int index = 0;

			while (addressList.size() > index) {

				ps.setString(1, addressList.get(index).get(1));
				ps.setString(2, addressList.get(index).get(2));
				ps.setString(3, addressList.get(index).get(3));
				ps.setString(4, addressList.get(index).get(4));
				ps.setString(5, addressList.get(index).get(5));
				ps.setString(6, addressList.get(index).get(6));
				ps.setInt(7, userId);

				ans = ps.executeUpdate();

				index++;
			}

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

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject fatchAddress(int userId) {

		// Select Query
		String sql = "SELECT * FROM user_address WHERE user_id=?";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			// JSONObject object to store JSON Array
			JSONObject jsonObject = new JSONObject();

			// JSON Array To Store Addresses
			JSONArray array = new JSONArray();

			while (rs.next()) {

				// Make <K,V> Of Array Element
				JSONObject record = new JSONObject();

				// Inserting key-value pairs into the json object
				record.put("AddressId", rs.getInt(1));
				record.put("home", rs.getString(2));
				record.put("landmark", rs.getString(3));
				record.put("city", rs.getString(4));
				record.put("state", rs.getString(5));
				record.put("country", rs.getString(6));
				record.put("zipcode", rs.getString(7));

				// Add to JSON Array
				array.add(record);

			}

			// Add JSON Array To JSONObject
			jsonObject.put("addressList", array);

			return jsonObject;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean update(List<List<String>> updateList, int userId) {

		// Update Query
		String sql = "UPDATE user_address SET line_1=?,line_2=?,city=?,state=?,country=?,zipcode=? WHERE address_id=? AND user_id=?";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			// Set auto-commit to false
			con.setAutoCommit(false);

			// initlize index
			int index = 0;

			while (updateList.size() > index) {

				ps.setString(1, updateList.get(index).get(1));
				ps.setString(2, updateList.get(index).get(2));
				ps.setString(3, updateList.get(index).get(3));
				ps.setString(4, updateList.get(index).get(4));
				ps.setString(5, updateList.get(index).get(5));
				ps.setString(6, updateList.get(index).get(6));
				ps.setInt(7, Integer.parseInt(updateList.get(index).get(0)));
				ps.setInt(8, userId);

				// adding batch
				ps.addBatch();

				// index increment
				index++;
			}

			// store batch execution result
			int[] ans = ps.executeBatch();

			// Explicitly commit statements to apply changes
			con.commit();

			for (int count = 0; count < ans.length; count++) {

				// check query result
				if (ans[count] < 0) {
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean remoeUserAddress(int userId) {

		// delete query
		String sql = "DELETE FROM user_address WHERE user_id=?";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

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
	public boolean remoeSelectedAddress(int addressId) {

		// delete query
		String sql = "DELETE FROM user_address WHERE address_id=?";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, addressId);

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
	public boolean fatchAddressCount(int userId) {

		// address count query
		String sql = "SELECT COUNT( * ) FROM user_address WHERE user_id=?";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			int ans = 0;
			if (rs.next()) {
				ans = rs.getInt(1);
			}

			if (ans > 1) {
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
	public List<String> fatchAllAddressIds(int userId) {
		
		// address count query
		String sql = "SELECT address_id FROM user_address WHERE user_id=?";
		
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			//List of user Id
			List<String> addressId  = new ArrayList<String>();
			
			while (rs.next()) {
				addressId.add(Integer.toString(rs.getInt(1)));
			}

			return addressId;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteUserAddress(List<String> addressIds) {

		// Update Query
		String sql = "DELETE FROM user_address WHERE address_id=?";

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			// Set auto-commit to false
			con.setAutoCommit(false);

			// initlize index
			int index = 0;

			while (addressIds.size() > index) {

				ps.setString(1, addressIds.get(index));

				// adding batch
				ps.addBatch();

				// index increment
				index++;
			}

			// store batch execution result
			int[] ans = ps.executeBatch();

			// Explicitly commit statements to apply changes
			con.commit();

			for (int count = 0; count < ans.length; count++) {

				// check query result
				if (ans[count] < 0) {
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

}
