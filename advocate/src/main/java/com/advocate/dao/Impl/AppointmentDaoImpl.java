package com.advocate.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.advocate.dao.AppointmentDao;
import com.advocate.entity.Appointment;

public class AppointmentDaoImpl implements AppointmentDao {

	List<Appointment> appointments = new ArrayList<>();

	@Override
	public boolean addAppointment(Appointment appointment) throws SQLException {

		return appointments.add(appointment);
	}

	@Override
	public List<Appointment> getAllAppointments() {

		return appointments;
	}

	@Override
	public void editAppointment(int id, Appointment appointment) {

	}

	@Override
	public Appointment getAppointmentById(int id) {

		return new Appointment();
	}

	@Override
	public void deleteAppointmentById(int id) {

	}

}
