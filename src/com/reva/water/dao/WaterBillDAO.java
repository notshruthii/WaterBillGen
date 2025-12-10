package com.reva.water.dao;
import java.sql.*;
import com.reva.water.bean.WaterBillBean;
import com.reva.water.util.*;

public class WaterBillDAO {
	public String createWaterBill(WaterBillBean waterBill)throws Exception
	{   String query="insert into waterbill_tb(month,year,start_reading,curr_reading,consumption,conn_type,fixed,var_charges,total_charges) values( ?,?,?,?,?,?,?,?,?)";
		Connection con=DBUtil.getDBConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,waterBill.getBillMonth());
		pst.setString(2, waterBill.getYear());
		pst.setLong(3,waterBill.getStartReading());
		pst.setLong(4,waterBill.getCurrentReading());
		pst.setLong(5,waterBill.getTotalConsumption());
		pst.setString(6,waterBill.getConnectionType());
		pst.setDouble(7,waterBill.getFixedCharges());
		pst.setDouble(8,waterBill.getVariableCharges());
		pst.setDouble(9,waterBill.getTotalCharges());
		
		ResultSet res=pst.executeQuery();
		if(res != null)
		{
			return "SUCCESS";
		}
		else
		{
			return  "FAIL";
		}
	}
	public WaterBillBean fetchWaterBill(int consumerNumber,String billMonth,String year)throws Exception
	{
		String query="select * from waterbill_tb where consumerNumber=? and billMonth=? and year=?";
		Connection con=DBUtil.getDBConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, consumerNumber);
		pst.setString(2,billMonth);
		pst.setString(3, year);
		
		ResultSet res=pst.executeQuery();
		WaterBillBean bill = null;//new WaterBillBean();
	    
	    
	    if (res.next()) {
	        bill=new WaterBillBean();
	        bill.setConsumerNumber(res.getInt("consumerNumber"));
	        bill.setBillMonth(res.getString("billMonth"));
	        bill.setYear(res.getString("year"));
	        bill.setStartReading(res.getInt("start_reading"));
	        bill.setCurrentReading(res.getInt("curr_reading"));
	        bill.setTotalConsumption(res.getInt("consumption"));

	        bill.setConnectionType(res.getString("conn_type"));

	        bill.setFixedCharges(res.getDouble("fixed"));
	        bill.setVariableCharges(res.getDouble("var_charges"));
	        bill.setTotalCharges(res.getDouble("total_charges"));
	    }
	    return bill;
	
	}
	public boolean waterBillExists(int consumerNumber,String billMonth,String year)throws Exception
	{
		String query="select * from waterbill_tb where consumerNumber=? and billMonth=? and year=?";
		Connection con=DBUtil.getDBConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, consumerNumber);
		pst.setString(2,billMonth);
		pst.setString(3, year);
		
		ResultSet res=pst.executeQuery();
		if(res != null)
		{
			return true;
		}
		else
		{
			return  false;
		}
	}

}
