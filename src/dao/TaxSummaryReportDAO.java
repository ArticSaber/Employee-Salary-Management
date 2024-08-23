package dao;

import java.util.List;

import pojo.TaxSummaryReport;

public interface TaxSummaryReportDAO {
	void generateReport(TaxSummaryReport report);

	List<TaxSummaryReport> getAllReportsByUserID(int userID);

	List<TaxSummaryReport> getAllReports();
}
