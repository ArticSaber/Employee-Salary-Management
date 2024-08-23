package pojo;

import java.util.Date;

public class InvestmentDetails {
	private int investmentID;
	private int userID;
	private double income; // Total income for tax calculation
	private double licPremium; // LIC Premium paid
	private double ppfInvestment; // PPF investment
	private double shortTermGains; // Short-term capital gains
	private double longTermGains; // Long-term capital gains
	private int year; // Financial year for the report
	private Date investmentDate; // Date of investment
	private String taxRegime; // Tax regime selected by the user (Old/New)

	public InvestmentDetails() {
	}

	public InvestmentDetails(int investmentID, int userID, double income, double licPremium, double ppfInvestment,
			double shortTermGains, double longTermGains, int year, Date investmentDate, String taxRegime) {
		this.investmentID = investmentID;
		this.userID = userID;
		this.income = income;
		this.licPremium = licPremium;
		this.ppfInvestment = ppfInvestment;
		this.shortTermGains = shortTermGains;
		this.longTermGains = longTermGains;
		this.year = year;
		this.investmentDate = investmentDate;
		this.taxRegime = taxRegime;
	}

	// Getters and Setters

	public int getInvestmentID() {
		return investmentID;
	}

	public void setInvestmentID(int investmentID) {
		this.investmentID = investmentID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getLicPremium() {
		return licPremium;
	}

	public void setLicPremium(double licPremium) {
		this.licPremium = licPremium;
	}

	public double getPpfInvestment() {
		return ppfInvestment;
	}

	public void setPpfInvestment(double ppfInvestment) {
		this.ppfInvestment = ppfInvestment;
	}

	public double getShortTermGains() {
		return shortTermGains;
	}

	public void setShortTermGains(double shortTermGains) {
		this.shortTermGains = shortTermGains;
	}

	public double getLongTermGains() {
		return longTermGains;
	}

	public void setLongTermGains(double longTermGains) {
		this.longTermGains = longTermGains;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getInvestmentDate() {
		return investmentDate;
	}

	public void setInvestmentDate(Date investmentDate) {
		this.investmentDate = investmentDate;
	}

	public String getTaxRegime() {
		return taxRegime;
	}

	public void setTaxRegime(String taxRegime) {
		this.taxRegime = taxRegime;
	}
}
