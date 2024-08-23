package service;

import java.util.List;

import pojo.TaxSummaryReport;
import pojo.UserFinancialDetails;

public interface TaxSummaryReportService {
	void generateReport(TaxSummaryReport report);

	List<TaxSummaryReport> getAllReportsByUserID(int userID);

	TaxSummaryReport generateTaxSummary(UserFinancialDetails financialDetails);

	TaxSummaryReport generateReport(int userID);

	List<TaxSummaryReport> getAllReports();
}
