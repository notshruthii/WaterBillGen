package com.reva.water.servlets;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;


import com.reva.water.bean.WaterBillBean;
import com.reva.water.service.Administrator;
public class MainServlet extends HttpServlet{
	public String generateWaterBill(HttpServletRequest request) throws Exception
	{
		
		WaterBillBean bill=new WaterBillBean();
		
		bill.setConsumerNumber(Integer.parseInt(request.getParameter("consumerNumber")));
        bill.setBillMonth(request.getParameter("billMonth"));
        bill.setYear(request.getParameter("year"));
        bill.setStartReading(Integer.parseInt(request.getParameter("start_reading")));
        bill.setCurrentReading(Integer.parseInt(request.getParameter("curr_reading")));
        bill.setTotalConsumption(Integer.parseInt(request.getParameter("consumption")));
        bill.setConnectionType(request.getParameter("conn_type"));
        bill.setFixedCharges(Double.parseDouble(request.getParameter("fixed")));
        bill.setVariableCharges(Double.parseDouble(request.getParameter("var_charges")));
        bill.setTotalCharges(Double.parseDouble(request.getParameter("total_charges")));
        
        Administrator ad=new Administrator();
        String result=ad.addWaterBill(bill);
        
        return result;
	}
	public WaterBillBean viewWaterBill(HttpServletRequest request) throws Exception
	{
		WaterBillBean bill = null;
		
	    int consumerNumber = Integer.parseInt(request.getParameter("consumerNumber"));
	        String billMonth = request.getParameter("billMonth");
	        String year = request.getParameter("year");

	        Administrator admin = new Administrator();
	        bill = admin.viewWaterBill(consumerNumber, billMonth, year);
	        
	        return bill;
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		response.setContentType("text/html");
		String operation=request.getParameter("operation");
		try {
			if(operation.equalsIgnoreCase("generate"))
			{
				String res=generateWaterBill(request);
				if(res.equalsIgnoreCase("FAIL") || res==null )
				{
					response.sendRedirect("error.html");
				}
				else
				{
					response.sendRedirect("success.html");
				}
			}
			else if(operation.equalsIgnoreCase("view"))
			{
				WaterBillBean res=viewWaterBill(request);
				if(res==null )
				{
					request.setAttribute("message", "No matching records exists! Please try again!");
			        request.getRequestDispatcher("displayBill.jsp").forward(request, response);
			    } 
				else
				{
			        request.setAttribute("waterBill", res);
			        request.getRequestDispatcher("displayBill.jsp").forward(request, response);
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
