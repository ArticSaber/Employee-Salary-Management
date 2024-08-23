package DaoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.UserFinancialDetailsDAO;
import pojo.UserFinancialDetails;

public class UserFinancialDetailsDAOImpl implements UserFinancialDetailsDAO {
	private static final Map<Integer, UserFinancialDetails> financialDetailsDB = new HashMap<>();
	private static int financialCounter = 1;

	static {
		// Create some static financial details for demonstration
		financialDetailsDB.put(1, new UserFinancialDetails(1, 2023, 50000, 10000, 5000,
				Map.of("Investments", 20000.0, "Insurance", 10000.0), "New", new Date(), new Date()));

		financialDetailsDB.put(2, new UserFinancialDetails(2, 2023, 60000, 15000, 7000,
				Map.of("Investments", 30000.0, "Insurance", 12000.0), "Old", new Date(), new Date()));

		financialDetailsDB.put(3, new UserFinancialDetails(3, 2023, 70000, 20000, 9000,
				Map.of("Investments", 25000.0, "Insurance", 15000.0), "New", new Date(), new Date()));
	}

	@Override
	public UserFinancialDetails getFinancialDetailsByUserID(int userID) {
		if (financialDetailsDB.containsKey(userID)) {
			return financialDetailsDB.get(userID);
		}
		return null;
	}

	@Override
	public void addFinancialDetails(UserFinancialDetails details) {
		details.setFinancialID(financialCounter++);
		financialDetailsDB.put(details.getFinancialID(), details);
	}

	@Override
	public UserFinancialDetails findByUserIDAndYear(int userID, int year) {
		return financialDetailsDB.values().stream()
				.filter(details -> details.getUserID() == userID && details.getYear() == year).findFirst().orElse(null);
	}

	@Override
	public void updateFinancialDetails(UserFinancialDetails details) {
		financialDetailsDB.put(details.getFinancialID(), details);
	}

	@Override
	public void deleteFinancialDetails(int financialID) {
		financialDetailsDB.remove(financialID);
	}

	@Override
	public UserFinancialDetails findById(int financialID) {
		return financialDetailsDB.get(financialID);
	}

	@Override
	public List<UserFinancialDetails> getAllDetails() {
		return new ArrayList<>(financialDetailsDB.values());
	}
}
