package com.advocate.entity;


public class Appointment {
	private int apointmentid;
	private int customerId;
	private int advocateId;
	private String appointmentDate;
	private String appointmentTime;
	public int getApointmentId() {
		return apointmentid;
	}
	public void setApointmentId(int apointmentid) {
		this.apointmentid = apointmentid;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getAdvocateId() {
		return advocateId;
	}
	public void setAdvocateId(int advocateId) {
		this.advocateId = advocateId;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
	
	@Override
	public String toString() {
		return "Appointment [apointmentid=" + apointmentid + ", customerId=" + customerId + ", advocateId=" + advocateId
				+ ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + "]";
	}
	
	
	
	
	

}
