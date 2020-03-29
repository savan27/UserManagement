/**
 * 
 */
package com.savan.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author SAVAN
 *
 */
public class DBConnection {

	//Connection class object
	private static Connection con = null;
	
	//synchronized method 
	public static Connection getConnection() {
		if (con == null) {
			synchronized (Connection.class) {
				//propeties file
				String url = "jdbc:mysql://localhost:3306/user_detail";
				String db_user = "root";
				String db_password = "Root@123";

				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection(url, db_user, db_password);
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		return con;
	}
	
}
