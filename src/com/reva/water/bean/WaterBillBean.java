package com.reva.water.bean;

public class WaterBillBean {
	
	public int getConsumerNumber() {
		return consumerNumber;
	}
	public void setConsumerNumber(int consumerNumber) {
		this.consumerNumber = consumerNumber;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public long getStartReading() {
		return startReading;
	}
	public void setStartReading(long startReading) {
		this.startReading = startReading;
	}
	public long getCurrentReading() {
		return currentReading;
	}
	public void setCurrentReading(long currentReading) {
		this.currentReading = currentReading;
	}
	public long getTotalConsumption() {
		return totalConsumption;
	}
	public void setTotalConsumption(long totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	public double getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(double fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public double getVariableCharges() {
		return variableCharges;
	}
	public void setVariableCharges(double variableCharges) {
		this.variableCharges = variableCharges;
	}
	public double getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(double totalCharges) {
		this.totalCharges = totalCharges;
	}
	private int consumerNumber;
	private String billMonth;
	private String year;
	private long startReading;
	private long currentReading;
	private long totalConsumption;
	private String connectionType;
	private double fixedCharges;
	private double variableCharges;
	private double totalCharges;
	

}
