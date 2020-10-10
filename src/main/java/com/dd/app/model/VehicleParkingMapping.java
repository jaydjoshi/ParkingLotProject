package com.dd.app.model;

import java.util.Date;

public class VehicleParkingMapping {
	
	private int vpId;
	private Date startTime;
	private Date endTime;
	private int amount;
	private int pUnitId;
	private String vehicleNumber;
	
	public int getVpId() {
		return vpId;
	}
	public void setVpId(int vpId) {
		this.vpId = vpId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public int getpUnitId() {
		return pUnitId;
	}
	public void setpUnitId(int pUnitId) {
		this.pUnitId = pUnitId;
	}
	
	

}
