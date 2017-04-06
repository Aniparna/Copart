package com.copartchallange.dataconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MysqlConector {

	public static List fetchDetails(int custId, int id) {

		Connection connection = null;
		System.out.println("Enter the zipcode");
		Scanner sc = new Scanner(System.in);
		List<String> dataList = new ArrayList<String>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "Shona@1203");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		if (connection != null) {
			Statement stmt = null;
			try {
				stmt = connection.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (id == 1) {

				try {
					dataList = fetchCustomerDetails(stmt, custId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				dataList = fetchzipcode(stmt);
			}

		} else {
			System.out.println("Failed to make connection!");
		}
		return dataList;

	}

	private static List fetchzipcode(Statement stmt) {
		List<String> zip_code = new ArrayList<String>();

		try {
			ResultSet rs = stmt.executeQuery("select zipcode from copart_facility");
			while (rs.next()) {
				zip_code.add(rs.getString(1));
				System.out.print(rs.getString(1) + "," + rs.getString(2) + "\n");
			}

			return zip_code;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zip_code;

	}

	private static List fetchCustomerDetails(Statement stmt, int custId) throws SQLException {
		List<String> cust_details = new ArrayList<String>();
		ResultSet rs = stmt
				.executeQuery("select out_of_state,yard from customer_details where cust_id= '" + custId + "'");
		while (rs.next()) {
			cust_details.add(rs.getString(1));
			cust_details.add(rs.getString(2));

			System.out.print(rs.getString(1) + "," + rs.getString(2) + "\n");
		}

		return cust_details;

	}

}
