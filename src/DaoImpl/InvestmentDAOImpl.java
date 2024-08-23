package DaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.InvestmentDAO;
import pojo.InvestmentDetails;

public class InvestmentDAOImpl implements InvestmentDAO {
	// In-memory database for storing investment details
	private static final Map<Integer, InvestmentDetails> investmentDetailsDB = new HashMap<>();
	private static int investmentCounter = 1;

	@Override
	public void addInvestmentDetails(InvestmentDetails details) {
		details.setInvestmentID(investmentCounter++);
		investmentDetailsDB.put(details.getInvestmentID(), details);
	}

	@Override
	public InvestmentDetails findByUserIDAndYear(int userID, int year) {
		return investmentDetailsDB.values().stream()
				.filter(details -> details.getUserID() == userID && details.getYear() == year).findFirst().orElse(null);
	}

	@Override
	public void updateInvestmentDetails(InvestmentDetails details) {
		if (investmentDetailsDB.containsKey(details.getInvestmentID())) {
			investmentDetailsDB.put(details.getInvestmentID(), details);
		} else {
			throw new RuntimeException("Investment details not found.");
		}
	}

	@Override
	public void deleteInvestmentDetails(int investmentID) {
		if (investmentDetailsDB.containsKey(investmentID)) {
			investmentDetailsDB.remove(investmentID);
		} else {
			throw new RuntimeException("Investment details not found.");
		}
	}

	@Override
	public List<InvestmentDetails> getAllInvestmentDetails() {
		return new ArrayList<>(investmentDetailsDB.values());
	}
}
