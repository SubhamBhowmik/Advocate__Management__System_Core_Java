package com.advocate.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.advocate.dao.AppointmentDao;
import com.advocate.entity.Appointment;

import com.advocate.util.DataBaseConfig;

public class AppointmentDaoDbImpl implements AppointmentDao {

	private Connection connection = DataBaseConfig.getConnection();

	private static final String ADD_APPOINTMENT = "insert into appointments (appointmentId,customerId,advocateId,appointmentDate,appointmentTime) values(?,?,?,?,?)";

	@Override
	public boolean addAppointment(Appointment appointment) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(ADD_APPOINTMENT);
		ps.setInt(1, appointment.getApointmentId());
		ps.setInt(2, appointment.getCustomerId());
		ps.setInt(3, appointment.getAdvocateId());
		ps.setString(4, appointment.getAppointmentDate());
		ps.setString(5, appointment.getAppointmentTime());

		// Execute Statement
		int executeUpdate = ps.executeUpdate();
		System.out.println("\n >>>>Appointment Booked Successfully.");
		ps.close();
		if (executeUpdate > 0) {
			return true;
		}
		return false;
	}

	private static final String ALL_APPOINTMENTS = "select * from appointments";

	@Override
	public List<Appointment> getAllAppointments() {

		List<Appointment> appointmentCollection = new ArrayList<>();

		try {
			PreparedStatement ps = connection.prepareStatement(ALL_APPOINTMENTS);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				Appointment appointment = new Appointment();
				appointment.setApointmentId(result.getInt("appointmentId"));
				appointment.setCustomerId(result.getInt("customerId"));
				appointment.setAdvocateId(result.getInt("advocateId"));
				appointment.setAppointmentDate(result.getString("appointmentDate"));
				appointment.setAppointmentTime(result.getString("appointmentTime"));

				appointmentCollection.add(appointment);
			}

			result.close();
			ps.close();

		} catch (SQLException e) {

			System.out.println("Error:" + e);
		}
		return appointmentCollection;
	}

	private static final String UPDATE_QUERY = "UPDATE appointments SET customerId=?, advocateId=?, appointmentDate=?, appointmentTime=? WHERE appointmentId=?";

	@Override
	public void editAppointment(int id, Appointment appointment) {
		try {
			

			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

			preparedStatement.setInt(1, appointment.getCustomerId());
			preparedStatement.setInt(2, appointment.getAdvocateId());
			preparedStatement.setString(3, appointment.getAppointmentDate());
			preparedStatement.setString(4, appointment.getAppointmentTime());
			preparedStatement.setInt(5, id);

			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Appointment information updated successfully in the database.");
			} else {
				System.out.println("Failed to update appointment information in the database.");
			}

			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println();;
		}

	}
	
	private static final String FIND_BY_ID = "select * from appointments where appointmentId=?";

	@Override
	public Appointment getAppointmentById(int id) {
		Appointment appointment = new Appointment();
		try {
		
			PreparedStatement ps = connection.prepareStatement(FIND_BY_ID);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();

			if (result.next()) {
				appointment.setApointmentId(result.getInt("appointmentId"));;
				appointment.setCustomerId(result.getInt("customerId"));
				appointment.setAdvocateId(result.getInt("advocateId"));
				appointment.setAppointmentDate(result.getString("appointmentDate"));
				appointment.setAppointmentTime(result.getString("appointmentTime"));
			
			}else {
				System.out.println("No result found on this id.");
				return null;
			}
			
		

		} catch (SQLException e) {
			System.out.println("Error");
		}
		
		return appointment;
	}

	 private static final String DELETE_BY_ID="delete from appointments where appointmentId=?";
	@Override
	public void deleteAppointmentById(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID);
			ps.setInt(1, id);
			 int affectedRows = ps.executeUpdate();
		        
		        if (affectedRows > 0) {
		            System.out.println("Appointment with ID " + id + " deleted successfully.");
		        } else {
		            System.out.println("Appointment with ID " + id + " not found or not deleted.");
		        }
		        
		        ps.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
