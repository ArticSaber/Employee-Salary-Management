package pojo;

import java.util.Date;
import java.util.Map;

public class UserFinancialDetails {
	private static int financialIdCounter = 1;
	private int financialID;
	private int userID;
	private int year;
	private double income;
	private double deductions;
	private double taxPaid;
	private Map<String, Double> investmentDetails;
	private String taxRegime;
	private Date createdAt;
	private Date updatedAt;

	public UserFinancialDetails() {
	}

	public UserFinancialDetails(int userID, int year, double income, double deductions, double taxPaid,
			Map<String, Double> investmentDetails, String taxRegime, Date createdAt, Date updatedAt) {
		this.financialID = financialIdCounter++;
		this.userID = userID;
		this.year = year;
		this.income = income;
		this.deductions = deductions;
		this.taxPaid = taxPaid;
		this.investmentDetails = investmentDetails;
		this.taxRegime = taxRegime;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UserFinancialDetails(int financialID, int userID, int year, double income, double deductions, double taxPaid,
			Map<String, Double> investmentDetails, String taxRegime, Date createdAt, Date updatedAt) {
		this.financialID = financialIdCounter++;
		this.userID = userID;
		this.year = year;
		this.income = income;
		this.deductions = deductions;
		this.taxPaid = taxPaid;
		this.investmentDetails = investmentDetails;
		this.taxRegime = taxRegime;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getFinancialID() {
		return financialID;
	}

	public void setFinancialID(int financialID) {
		this.financialID = financialID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getDeductions() {
		return deductions;
	}

	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	public double getTaxPaid() {
		return taxPaid;
	}

	public void setTaxPaid(double taxPaid) {
		this.taxPaid = taxPaid;
	}

	public Map<String, Double> getInvestmentDetails() {
		return investmentDetails;
	}

	public void setInvestmentDetails(Map<String, Double> investmentDetails) {
		this.investmentDetails = investmentDetails;
	}

	public String getTaxRegime() {
		return taxRegime;
	}

	public void setTaxRegime(String taxRegime) {
		this.taxRegime = taxRegime;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "UserFinancialDetails " + "financialID=" + financialID + ", userID=" + userID + ", year=" + year
				+ ", income=" + income + ", deductions=" + deductions + ", taxPaid=" + taxPaid + ", investmentDetails="
				+ investmentDetails + ", taxRegime='" + taxRegime + '\'' + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + '\n';
	}

}
