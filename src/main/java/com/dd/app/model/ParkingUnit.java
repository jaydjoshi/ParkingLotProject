package com.dd.app.model;

public class ParkingUnit {
	
	private int pUnitId;
	private String type;
	private int total;
	private int pLotId;
	
	private int current;
	
	public int getpUnitId() {
		return pUnitId;
	}
	public void setpUnitId(int pUnitId) {
		this.pUnitId = pUnitId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getpLotId() {
		return pLotId;
	}
	public void setpLotId(int pLotId) {
		this.pLotId = pLotId;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}

	
	
}
