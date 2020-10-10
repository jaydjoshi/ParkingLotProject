package com.dd.app.dao;

import com.dd.app.model.ParkingUnit;
import com.dd.app.model.ParkingUnitRate;
import com.dd.app.model.Vehicle;
import com.dd.app.model.VehicleParkingMapping;

public interface ParkingDao {


	ParkingUnit fetchParkingUnitById(int parkingUnitId);

	void insertVehicleParkingMapping(Vehicle vehicle, int parkingLotId, int parkingUnitId);

	void updateParkingUnit(ParkingUnit unit, Vehicle vehicle, int parkingLotId, int parkingUnitId);

	VehicleParkingMapping fetchVehicleParkingMapping(Vehicle vehicle, int parkingLotId, int parkingUnitId);

	ParkingUnitRate fetchParkingUnitRate(int parkingUnitId);

	void updateVehicleParkingMapping(Vehicle vehicle, int parkingUnitId);

}
