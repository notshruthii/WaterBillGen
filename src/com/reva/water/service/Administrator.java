package com.reva.water.service;

import com.reva.water.bean.WaterBillBean;
import com.reva.water.dao.WaterBillDAO;
import com.reva.water.util.*;
public class Administrator {
	public String addWaterBill(WaterBillBean waterBill)throws Exception
	{
			if(waterBill==null || waterBill.getConsumerNumber()==0 || waterBill.getBillMonth()==null || waterBill.getStartReading()==0 || waterBill.getCurrentReading()==0 || waterBill.getConnectionType()==null )
			{
				throw new InvalidInputException("Invalid input");
			}
			if(!waterBill.getConnectionType().equals("Domestic") && !waterBill.getConnectionType().equals("Industrial"))
			{
				throw new InvalidInputException("Invalid input");

			}
			if(waterBill.getCurrentReading()<waterBill.getStartReading())
			{
				throw new InvalidInputException("Invalid input");

			}
			WaterBillDAO dao=new WaterBillDAO();
			boolean exists=dao.waterBillExists(waterBill.getConsumerNumber(), waterBill.getBillMonth(), waterBill.getYear());
			if(exists)
			{
				return "Already Exists";
			}
			
			//totalConsumption=currentReading-startReading. 
			double var_charg;
			double tcharg;

			if(waterBill.getConnectionType().equals("Domestic"))
			{
				double fcharg=20;
								double total=waterBill.getCurrentReading()-waterBill.getStartReading();
				
					
					if(total<=5000)
					{var_charg=total*0.004;}
										
					else if(total<=10001)
					{
						var_charg=(5000*0.004)+((total-5000)*0.01);
						 						
					}
					else if(total<=15000)
					{
						var_charg=(5000*0.004)+(5000*0.01)+((total-10000)*0.015);
					}
					else 
					{
						var_charg=(5000*0.004)+(5000*0.01)+(5000*0.015)+((total-15000)*0.025);

					}
					tcharg=fcharg+var_charg;
					waterBill.setTotalCharges(tcharg);
				
			}
			if(waterBill.getConnectionType().equals("Industrial"))
			{
				double fcharg;
				
				
				double total=waterBill.getCurrentReading()-waterBill.getStartReading();
				if(total<=10000) {fcharg=150;}
				else {fcharg=200;}
					
					if(total<=5000)
					{var_charg=total*0.015;}
										
					else if(total<=10001)
					{
						var_charg=(5000*0.015)+((total-5000)*0.02);
						 						
					}
					else if(total<=15000)
					{
						var_charg=(5000*0.015)+(5000*0.02)+((total-10000)*0.025);
					}
					else 
					{
						var_charg=(5000*0.015)+(5000*0.02)+(5000*0.025)+((total-15000)*0.035);

					}
					tcharg=fcharg+var_charg;
					waterBill.setTotalCharges(tcharg);
			}
			
			return dao.createWaterBill(waterBill);
			
			
		}

	public WaterBillBean viewWaterBill(int consumerNumber,String billMonth,String year) throws Exception
	{
		WaterBillDAO dao=new WaterBillDAO();
		WaterBillBean bean=dao.fetchWaterBill(consumerNumber, billMonth, year);
		return bean;
		
	}

}
