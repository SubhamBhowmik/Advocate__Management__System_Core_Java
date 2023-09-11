package com.advocate.dao;

import java.sql.SQLException;
import java.util.List;

import com.advocate.entity.Appointment;


public interface AppointmentDao {

	boolean addAppointment(Appointment appointment) throws SQLException;

	List<Appointment> getAllAppointments();

	void editAppointment(int id, Appointment appointment);

	Appointment getAppointmentById(int id);

	void deleteAppointmentById(int id);

}
