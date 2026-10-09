package com.reva.water.service;

import com.reva.water.bean.WaterBillBean;
import com.reva.water.dao.WaterBillDAO;
import com.reva.water.util.*;

public class Administrator {

	public String addWaterBill(WaterBillBean waterBill) throws Exception
	{
		if (waterBill == null || waterBill.getConsumerNumber() == 0 || waterBill.getBillMonth() == null
				|| waterBill.getYear() == null || waterBill.getStartReading() == 0
				|| waterBill.getCurrentReading() == 0 || waterBill.getConnectionType() == null)
		{
			throw new InvalidInputException("Invalid input");
		}
		if (!waterBill.getConnectionType().equals("Domestic") && !waterBill.getConnectionType().equals("Industrial"))
		{
			throw new InvalidInputException("Invalid input");
		}
		if (waterBill.getCurrentReading() < waterBill.getStartReading())
		{
			throw new InvalidInputException("Invalid input");
		}

		WaterBillDAO dao = new WaterBillDAO();
		if (dao.waterBillExists(waterBill.getConsumerNumber(), waterBill.getBillMonth(), waterBill.getYear()))
		{
			return "Already Exists";
		}

		// totalConsumption = currentReading - startReading
		long total = waterBill.getCurrentReading() - waterBill.getStartReading();
		double fixed;
		double variable;

		if (waterBill.getConnectionType().equals("Domestic"))
		{
			fixed = 20;
			if (total <= 5000)
				variable = total * 0.004;
			else if (total <= 10000)
				variable = (5000 * 0.004) + ((total - 5000) * 0.01);
			else if (total <= 15000)
				variable = (5000 * 0.004) + (5000 * 0.01) + ((total - 10000) * 0.015);
			else
				variable = (5000 * 0.004) + (5000 * 0.01) + (5000 * 0.015) + ((total - 15000) * 0.025);
		}
		else // Industrial
		{
			fixed = (total <= 10000) ? 150 : 200;
			if (total <= 5000)
				variable = total * 0.015;
			else if (total <= 10000)
				variable = (5000 * 0.015) + ((total - 5000) * 0.02);
			else if (total <= 15000)
				variable = (5000 * 0.015) + (5000 * 0.02) + ((total - 10000) * 0.025);
			else
				variable = (5000 * 0.015) + (5000 * 0.02) + (5000 * 0.025) + ((total - 15000) * 0.035);
		}

		// Set every computed value on the bean so it is saved to the database
		waterBill.setTotalConsumption(total);
		waterBill.setFixedCharges(fixed);
		waterBill.setVariableCharges(variable);
		waterBill.setTotalCharges(fixed + variable);

		return dao.createWaterBill(waterBill);
	}

	public WaterBillBean viewWaterBill(int consumerNumber, String billMonth, String year) throws Exception
	{
		WaterBillDAO dao = new WaterBillDAO();
		return dao.fetchWaterBill(consumerNumber, billMonth, year);
	}

}