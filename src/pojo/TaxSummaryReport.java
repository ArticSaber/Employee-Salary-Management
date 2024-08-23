package pojo;

import java.util.Date;
import java.util.Map;

public class TaxSummaryReport {
	private int reportID;
	private int userID;
	private int year;
	private String taxRegime;
	private double salaryBeforeTax;
	private double salaryAfterTax;
	private Map<String, Double> taxSummary;
	private Date generatedAt;
	private double taxPaid;
	private double income;

	public TaxSummaryReport() {
	}

	public TaxSummaryReport(int reportID, int userID, int year, String taxRegime, double salaryBeforeTax,
			double salaryAfterTax, Map<String, Double> taxSummary, Date generatedAt) {
		this.reportID = reportID;
		this.userID = userID;
		this.year = year;
		this.taxRegime = taxRegime;
		this.salaryBeforeTax = salaryBeforeTax;
		this.salaryAfterTax = salaryAfterTax;
		this.taxSummary = taxSummary;
		this.taxPaid = taxPaid;

		this.generatedAt = generatedAt;
		this.income = income;
	}

	public double getIncome() {
		return income = salaryBeforeTax;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
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

	public double getTaxPaid() {
		return taxPaid = salaryBeforeTax - salaryAfterTax;
	}

	public void setTaxPaid(double tax) {
		this.taxPaid = tax;
	}

	public String getTaxRegime() {
		return taxRegime;
	}

	public void setTaxRegime(String taxRegime) {
		this.taxRegime = taxRegime;
	}

	public double getSalaryBeforeTax() {
		return salaryBeforeTax;
	}

	public void setSalaryBeforeTax(double salaryBeforeTax) {
		this.salaryBeforeTax = salaryBeforeTax;
	}

	public double getSalaryAfterTax() {
		return salaryAfterTax;
	}

	public void setSalaryAfterTax(double salaryAfterTax) {
		this.salaryAfterTax = salaryAfterTax;
	}

	public Map<String, Double> getTaxSummary() {
		return taxSummary;
	}

	public void setTaxSummary(Map<String, Double> taxSummary) {
		this.taxSummary = taxSummary;
	}

	public Date getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(Date generatedAt) {
		this.generatedAt = generatedAt;
	}
}
