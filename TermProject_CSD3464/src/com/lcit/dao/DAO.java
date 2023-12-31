package com.lcit.dao;

import java.sql.*;
import java.util.ArrayList;

import com.lcit.model.*;

public class DAO {

	private Connection setUpConnection() {
		Connection conn = null;

		final String USER = "root",
					 PASSWORD = "",
					 DB_URL = "jdbc:mysql://127.0.0.1:3307/patientrecords";

		try {
			// Load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Data base not Found");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public int insertRecord(Patient patient) {

		Connection conn = setUpConnection();

		String insert = "INSERT INTO patients (patient_id, first_name, last_name, date_of_birth, address, contact_number, email, details)"
					  + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement statement = conn.prepareStatement(insert);

			statement.setInt(1, patient.getId());
			statement.setString(2, patient.getFirstName());
			statement.setString(3, patient.getLastName());
			statement.setDate(4, patient.getDateOfBirth());
			statement.setString(5, patient.getAddress());
			statement.setString(6, patient.getContactNumber());
			statement.setString(7, patient.getEmail());
			statement.setString(8, patient.getDetails());
			statement.executeUpdate();
			conn.close();
			return 1;
		} catch (SQLIntegrityConstraintViolationException e) {
			return -1; // return -1 if record already exists
		} catch (SQLException e) {
			return 0; // return 0 if the insert statement is not successful
		}
	}

	public int updateRecord(Patient patient) {
		Connection conn = setUpConnection();

		String update = "UPDATE patients"
					  + " SET first_name = ?,"
					  + " last_name = ?,"
					  + " date_of_birth = ?,"
					  + " address = ?,"
					  + " contact_number = ?,"
					  + " email = ?,"
					  + " details = ?"
					  + " WHERE patient_id = ?";
		
		try {
			PreparedStatement statement = conn.prepareStatement(update);
			
			statement.setString(1, patient.getFirstName());
			statement.setString(2, patient.getLastName());
			statement.setDate(3, patient.getDateOfBirth());
			statement.setString(4, patient.getAddress());
			statement.setString(5, patient.getContactNumber());
			statement.setString(6, patient.getEmail());
			statement.setString(7, patient.getDetails());
			statement.setInt(8, patient.getId());

			return statement.executeUpdate();
		} catch(SQLException e) {
			return 0;
		}
	}
	
	public int deletRecord(Patient patient) {
		Connection conn = setUpConnection();

		String delete = "DELETE FROM patients "
					  + "WHERE patient_id = ?";
		
		try {
			PreparedStatement statement = conn.prepareStatement(delete);
			
			statement.setInt(1, patient.getId());
			
			return statement.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
			return 0;
		}
	}
	
	public ArrayList<Patient> selectRecords() {
		
		ArrayList<Patient> patients = new ArrayList<>();
		Connection conn = setUpConnection();
		
		String select = "SELECT * FROM patients";
		
		try {
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(select);
			
			while (result.next()) {
				Patient patient = new Patient(result.getInt(1),
											  result.getString(2),
											  result.getString(3),
											  result.getDate(4),
											  result.getString(5),
											  result.getString(6),
											  result.getString(7),
											  result.getString(8));
				patients.add(patient);
			}
			
			conn.close();
			return patients;
		} catch (SQLException e) {
			return null;
		}		
	}
}
