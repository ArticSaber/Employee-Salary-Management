package ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import dao.UserFinancialDetailsDAO;
import pojo.UserFinancialDetails;
import service.UserFinancialDetailsService;

public class UserFinancialDetailsServiceImpl implements UserFinancialDetailsService {
	private final UserFinancialDetailsDAO userFinancialDetailsDAO;

	// Constructor with dependency injection
	public UserFinancialDetailsServiceImpl(UserFinancialDetailsDAO userFinancialDetailsDAO) {
		this.userFinancialDetailsDAO = userFinancialDetailsDAO;
	}

	@Override
	public UserFinancialDetails getFinancialDetailsByUserID(int userID) {
		return getFinancialDetailsByUserID(userID);
	}

	@Override
	public void addFinancialDetails(UserFinancialDetails details) {
		details.setCreatedAt(new Date());
		details.setUpdatedAt(new Date());
		userFinancialDetailsDAO.addFinancialDetails(details);
	}

	@Override
	public UserFinancialDetails getFinancialDetails(int userID, int year) {
		return userFinancialDetailsDAO.findByUserIDAndYear(userID, year);
	}

	@Override
	public void updateFinancialDetails(UserFinancialDetails details) {
		details.setUpdatedAt(new Date());
		userFinancialDetailsDAO.updateFinancialDetails(details);
	}

	@Override
	public void deleteFinancialDetails(int financialID) {
		UserFinancialDetails details = userFinancialDetailsDAO.findById(financialID);
		if (details != null) {
			userFinancialDetailsDAO.deleteFinancialDetails(financialID);
		} else {
			throw new RuntimeException("Financial details not found for the given ID");
		}
	}

	@Override
	public List<UserFinancialDetails> getFinancialDetailsByTaxRegime(String taxRegime) {
		return userFinancialDetailsDAO.getAllDetails().stream()
				.filter(details -> details.getTaxRegime().equalsIgnoreCase(taxRegime)).collect(Collectors.toList());
	}
}
