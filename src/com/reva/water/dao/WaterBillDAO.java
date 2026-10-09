package com.reva.water.dao;
import java.sql.*;
import com.reva.water.bean.WaterBillBean;
import com.reva.water.util.*;

public class WaterBillDAO {

	

	public String createWaterBill(WaterBillBean waterBill) throws Exception
	{
		String query = "insert into waterbill_tb(consumerno,month,year,start_reading,curr_reading,consumption,conn_type,fixed,var_charges,total_charges) values(?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = DBUtil.getDBConnection();
		     PreparedStatement pst = con.prepareStatement(query))
		{
			pst.setInt(1, waterBill.getConsumerNumber());
			pst.setString(2, waterBill.getBillMonth());
			pst.setString(3, waterBill.getYear());
			pst.setLong(4, waterBill.getStartReading());
			pst.setLong(5, waterBill.getCurrentReading());
			pst.setLong(6, waterBill.getTotalConsumption());
			pst.setString(7, waterBill.getConnectionType());
			pst.setDouble(8, waterBill.getFixedCharges());
			pst.setDouble(9, waterBill.getVariableCharges());
			pst.setDouble(10, waterBill.getTotalCharges());

			// INSERT must use executeUpdate, not executeQuery
			int rows = pst.executeUpdate();
			return rows > 0 ? "SUCCESS" : "FAIL";
		}
	}

	public WaterBillBean fetchWaterBill(int consumerNumber, String billMonth, String year) throws Exception
	{
		String query = "select * from waterbill_tb where consumerno=? and month=? and year=?";
		try (Connection con = DBUtil.getDBConnection();
		     PreparedStatement pst = con.prepareStatement(query))
		{
			pst.setInt(1, consumerNumber);
			pst.setString(2, billMonth);
			pst.setString(3, year);

			try (ResultSet res = pst.executeQuery())
			{
				WaterBillBean bill = null;
				if (res.next()) {
					bill = new WaterBillBean();
					bill.setConsumerNumber(res.getInt("consumerno"));
					bill.setBillMonth(res.getString("month"));
					bill.setYear(res.getString("year"));
					bill.setStartReading(res.getLong("start_reading"));
					bill.setCurrentReading(res.getLong("curr_reading"));
					bill.setTotalConsumption(res.getLong("consumption"));
					bill.setConnectionType(res.getString("conn_type"));
					bill.setFixedCharges(res.getDouble("fixed"));
					bill.setVariableCharges(res.getDouble("var_charges"));
					bill.setTotalCharges(res.getDouble("total_charges"));
				}
				return bill;
			}
		}
	}

	public boolean waterBillExists(int consumerNumber, String billMonth, String year) throws Exception
	{
		String query = "select 1 from waterbill_tb where consumerno=? and month=? and year=?";
		try (Connection con = DBUtil.getDBConnection();
		     PreparedStatement pst = con.prepareStatement(query))
		{
			pst.setInt(1, consumerNumber);
			pst.setString(2, billMonth);
			pst.setString(3, year);

			try (ResultSet res = pst.executeQuery())
			{
				// A ResultSet is never null; check whether it has a row
				return res.next();
			}
		}
	}
}