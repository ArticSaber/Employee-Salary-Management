package ServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.TaxSummaryReportDAO;
import dao.UserFinancialDetailsDAO;
import pojo.TaxSummaryReport;
import pojo.UserFinancialDetails;
import service.TaxSummaryReportService;

public class TaxSummaryReportServiceImpl implements TaxSummaryReportService {
	private final TaxSummaryReportDAO taxSummaryReportDAO;
	private final UserFinancialDetailsDAO userFinancialDetailsDAO;

	public TaxSummaryReportServiceImpl(TaxSummaryReportDAO taxSummaryReportDAO,
			UserFinancialDetailsDAO userFinancialDetailsDAO) {
		this.taxSummaryReportDAO = taxSummaryReportDAO;
		this.userFinancialDetailsDAO = userFinancialDetailsDAO;
	}

	@Override
	public void generateReport(TaxSummaryReport report) {
		taxSummaryReportDAO.generateReport(report);
	}

	@Override
	public List<TaxSummaryReport> getAllReportsByUserID(int userID) {
		return taxSummaryReportDAO.getAllReportsByUserID(userID);
	}

	@Override
	public List<TaxSummaryReport> getAllReports() {
		return taxSummaryReportDAO.getAllReports();
	}

	@Override
	public TaxSummaryReport generateTaxSummary(UserFinancialDetails financialDetails) {
		double income = financialDetails.getIncome();
		double tax;
		Map<String, Double> taxSummary = new HashMap<>();
		String taxRegime = financialDetails.getTaxRegime();

		if ("Old".equalsIgnoreCase(taxRegime)) {
			tax = calculateOldRegimeTax(income);
		} else if ("New".equalsIgnoreCase(taxRegime)) {
			tax = calculateNewRegimeTax(income);
		} else {
			throw new RuntimeException("Invalid tax regime");
		}

		taxSummary.put("Total Tax", tax);

		TaxSummaryReport report = new TaxSummaryReport();
		report.setUserID(financialDetails.getUserID());
		report.setYear(financialDetails.getYear());
		report.setTaxRegime(taxRegime);
		report.setSalaryBeforeTax(income);
		report.setSalaryAfterTax(income - tax);
		report.setTaxSummary(taxSummary);
		report.setGeneratedAt(new Date());

		generateReport(report);

		return report;
	}

	@Override
	public TaxSummaryReport generateReport(int userID) {
		// Retrieve financial details for the given user ID
		UserFinancialDetails financialDetails = userFinancialDetailsDAO.getFinancialDetailsByUserID(userID);
		if (financialDetails == null) {
			throw new RuntimeException("No financial details found for user ID: " + userID);
		}

		// Generate tax summary report using the financial details
		return generateTaxSummary(financialDetails);
	}

	private double calculateOldRegimeTax(double income) {
		double tax = 0;
		if (income <= 250000) {
			tax = 0;
		} else if (income <= 500000) {
			tax = (income - 250000) * 0.05;
		} else if (income <= 1000000) {
			tax = 12500 + (income - 500000) * 0.20;
		} else {
			tax = 112500 + (income - 1000000) * 0.30;
		}
		return tax;
	}

	private double calculateNewRegimeTax(double income) {
		double tax = 0;
		if (income <= 250000) {
			tax = 0;
		} else if (income <= 500000) {
			tax = (income - 250000) * 0.05;
		} else if (income <= 750000) {
			tax = 12500 + (income - 500000) * 0.10;
		} else if (income <= 1000000) {
			tax = 37500 + (income - 750000) * 0.15;
		} else if (income <= 1250000) {
			tax = 75000 + (income - 1000000) * 0.20;
		} else if (income <= 1500000) {
			tax = 125000 + (income - 1250000) * 0.25;
		} else {
			tax = 187500 + (income - 1500000) * 0.30;
		}
		return tax;
	}
}