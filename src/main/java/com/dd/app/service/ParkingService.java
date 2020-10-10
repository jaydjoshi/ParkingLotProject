package com.dd.app.service;

import com.dd.app.model.Vehicle;

public interface ParkingService {

	boolean park(Vehicle vehicle, int parkingLotId, int parkingUnitId);

	int unpark(Vehicle vehicle, int parkingLotId, int parkingUnitId);

}
