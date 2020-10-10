package com.dd.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dd.app.model.ParkingUnit;
import com.dd.app.model.ParkingUnitRate;
import com.dd.app.model.Vehicle;
import com.dd.app.model.VehicleParkingMapping;

@Repository
public class ParkingDaoImpl implements ParkingDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @author jay
	 *
	 */
	class ParkingUnitRowMapper implements RowMapper<ParkingUnit> {
		@Override
		public ParkingUnit mapRow(ResultSet rs, int rowNum) throws SQLException {
			ParkingUnit pu = new ParkingUnit();
			pu.setCurrent(rs.getInt("CURRENT"));
			pu.setTotal(rs.getInt("TOTAL"));
			pu.setpUnitId(rs.getInt("P_UNIT_ID"));
			return pu;
		}

	}
	
	// ParkingUnitRateRowMapper
	/**
	 * 
	 * @author jay
	 *
	 */
	class ParkingUnitRateRowMapper implements RowMapper<ParkingUnitRate> {
		@Override
		public ParkingUnitRate mapRow(ResultSet rs, int rowNum) throws SQLException {
			ParkingUnitRate pu = new ParkingUnitRate();
			pu.setRate(rs.getInt("RATE"));
			pu.setpUnitId(rs.getInt("P_UNIT_ID"));
			pu.setFrequency(rs.getInt("FREQUENCY"));
			return pu;
		}

	}
	
	/**
	 * 
	 * @author jay
	 *
	 */
	class VehicleParkingMappingRowMapper implements RowMapper<VehicleParkingMapping> {
		@Override
		public VehicleParkingMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
			VehicleParkingMapping pu = new VehicleParkingMapping();
			pu.setVpId(rs.getInt("VEHICLE_PARKING_ID"));
			pu.setStartTime(rs.getDate("START_TIME"));
			pu.setEndTime(rs.getDate("END_TIME"));
			pu.setAmount(rs.getInt("AMOUNT"));
			pu.setpUnitId(rs.getInt("P_unit_ID"));
			pu.setVehicleNumber(rs.getString("VEHICLE_NUMBER"));
			return pu;
		}

	}


	
	@Override
	public ParkingUnit fetchParkingUnitById(int parkingUnitId) {
		List<ParkingUnit> units = jdbcTemplate.query("select * from PARKING_UNIT WHERE p_unit_id=? ", new Object[] { parkingUnitId }, new ParkingUnitRowMapper());
		ParkingUnit unit = units.get(0);
		return unit;
	}

	@Override
	public void insertVehicleParkingMapping(Vehicle vehicle, int parkingLotId, int parkingUnitId) {

		jdbcTemplate.update("insert into vehicle_parking_mapping (start_time, end_time, amount, P_UNIT_ID, VEHICLE_NUMBER  ) " + "values(  CURRENT_TIMESTAMP(), null, null, ?, ? )",
				new Object[] { parkingUnitId, vehicle.getNumber() });
	}

	@Override
	public void updateParkingUnit(ParkingUnit unit,Vehicle vehicle, int parkingLotId, int parkingUnitId) {

		// upadte current
		jdbcTemplate.update("UPDATE PARKING_UNIT SET CURRENT=? WHERE P_UNIT_ID=? " ,
							new Object[] { unit.getCurrent()+1, parkingUnitId });
		
	}

	@Override
	public VehicleParkingMapping fetchVehicleParkingMapping(Vehicle vehicle, int parkingLotId, int parkingUnitId) {
		List<VehicleParkingMapping>  vehicleMappings = jdbcTemplate.query("select * from vehicle_parking_mapping WHERE VEHICLE_NUMBER=? AND END_TIME IS NULL ", new Object[] { vehicle.getNumber() }, new VehicleParkingMappingRowMapper());
		
		VehicleParkingMapping mapping = vehicleMappings.get(0);
		return mapping;
	}

	@Override
	public ParkingUnitRate fetchParkingUnitRate(int parkingUnitId) {
		// PARKING_UNIT_RATE
		
		List<ParkingUnitRate>  rate = jdbcTemplate.query("select * from PARKING_UNIT_RATE WHERE p_unit_id =?", new Object[] { parkingUnitId }, new ParkingUnitRateRowMapper());
				
		return rate.get(0);
	}

	@Override
	public void updateVehicleParkingMapping(Vehicle vehicle, int parkingUnitId) {
		// update the end_date
		jdbcTemplate.update("UPDATE vehicle_parking_mapping SET END_TIME=CURRENT_TIMESTAMP() WHERE P_UNIT_ID=? AND VEHICLE_NUMBER=? " ,
						new Object[] {  parkingUnitId, vehicle.getNumber() });
		
	}
	
	

}
