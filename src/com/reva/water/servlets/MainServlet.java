package com.reva.water.servlets;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.reva.water.bean.WaterBillBean;
import com.reva.water.service.Administrator;

public class MainServlet extends HttpServlet {

	public String generateWaterBill(HttpServletRequest request) throws Exception
	{
		WaterBillBean bill = new WaterBillBean();

		bill.setConsumerNumber(Integer.parseInt(request.getParameter("consumerNumber")));
		bill.setBillMonth(request.getParameter("billMonth"));
		bill.setYear(request.getParameter("year"));
		
		// consumption, fixed, variable and total charges are calculated in
		// Administrator.addWaterBill(), so they are not read from the request.
		bill.setStartReading(Long.parseLong(request.getParameter("startReading")));
		bill.setCurrentReading(Long.parseLong(request.getParameter("currentReading")));
		bill.setConnectionType(request.getParameter("connectionType"));
		Administrator ad = new Administrator();
		return ad.addWaterBill(bill);
	}

	public WaterBillBean viewWaterBill(HttpServletRequest request) throws Exception
	{
		int consumerNumber = Integer.parseInt(request.getParameter("consumerNumber"));
		String billMonth = request.getParameter("billMonth");
		String year = request.getParameter("year");

		Administrator admin = new Administrator();
		return admin.viewWaterBill(consumerNumber, billMonth, year);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		String operation = request.getParameter("operation");
		try {
			if (operation == null) {
				response.sendRedirect("error.html");
				return;
			}
			if (operation.equalsIgnoreCase("generate"))
			{
				String res = generateWaterBill(request);
				// check null first, and treat only SUCCESS as success
				if (res != null && res.equalsIgnoreCase("SUCCESS"))
				{
					response.sendRedirect("success.html");
				}
				else
				{
					response.sendRedirect("error.html");
				}
			}
			else if (operation.equalsIgnoreCase("view"))
			{
				WaterBillBean res = viewWaterBill(request);
				if (res == null)
				{
					request.setAttribute("message", "No matching records exists! Please try again!");
				}
				else
				{
					request.setAttribute("waterBill", res);
				}
				request.getRequestDispatcher("displayBill.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (!response.isCommitted()) {
				response.sendRedirect("error.html");
			}
		}
	}

}