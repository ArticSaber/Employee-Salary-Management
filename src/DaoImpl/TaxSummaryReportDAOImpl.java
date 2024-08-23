package DaoImpl;

import java.util.ArrayList;
import java.util.List;

import dao.TaxSummaryReportDAO;
import pojo.TaxSummaryReport;

public class TaxSummaryReportDAOImpl implements TaxSummaryReportDAO {
	private static final List<TaxSummaryReport> reportsDB = new ArrayList<>();

	@Override
	public void generateReport(TaxSummaryReport report) {
		reportsDB.add(report);
	}

	@Override
	public List<TaxSummaryReport> getAllReportsByUserID(int userID) {
		List<TaxSummaryReport> reports = new ArrayList<>();
		for (TaxSummaryReport report : reportsDB) {
			if (report.getUserID() == userID) {
				reports.add(report);
			}
		}
		return reports;
	}

	@Override
	public List<TaxSummaryReport> getAllReports() {
		return new ArrayList<>(reportsDB);
	}
}
