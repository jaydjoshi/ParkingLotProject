package com.dd.app.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dd.app.dao.ParkingDao;
import com.dd.app.model.ParkingUnit;
import com.dd.app.model.ParkingUnitRate;
import com.dd.app.model.Vehicle;
import com.dd.app.model.VehicleParkingMapping;

@Service
public class ParkingServiceImpl implements ParkingService {
	
	@Autowired
	public ParkingDao parkingDao;


	/**
	 * 
	 */
	@Transactional
	@Override
	public boolean park(Vehicle vehicle, int parkingLotId, int parkingUnitId) {
		
		ParkingUnit unit = parkingDao.fetchParkingUnitById(parkingUnitId);
		
		if((unit.getCurrent() == unit.getTotal())) {
			return false;
		}else {
			//insert in mapping
		
			parkingDao.insertVehicleParkingMapping( vehicle, parkingLotId, parkingUnitId);
			parkingDao.updateParkingUnit(unit, vehicle, parkingLotId, parkingUnitId);
			
			return true;
		}
		
	}


	/**
	 * 
	 */
	@Transactional
	@Override
	public int unpark(Vehicle vehicle, int parkingLotId, int parkingUnitId) {
		VehicleParkingMapping mapping = parkingDao.fetchVehicleParkingMapping(vehicle, parkingLotId, parkingUnitId);
		
		Date start = mapping.getStartTime();
		Long end = System.currentTimeMillis();
		
		// check the difference
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(end);
		Date endDate = calendar.getTime();
		int hours = (int) (endDate.getTime() - start.getTime()) / 1000* 3600;
		
		ParkingUnitRate rate = parkingDao.fetchParkingUnitRate(parkingUnitId);
		parkingDao.updateVehicleParkingMapping(vehicle, parkingUnitId);
		
		return hours * rate.getRate() / rate.getFrequency();
	}

}
