package ServiceImpl;

import java.util.HashMap;
import java.util.Map;

import pojo.InvestmentDetails;
import service.InvestmentService;

public class InvestmentServiceImpl implements InvestmentService {
	private final Map<Integer, Map<Integer, InvestmentDetails>> investmentDetailsDB = new HashMap<>();

	@Override
	public String createInvestmentDetails(InvestmentDetails details) {
		investmentDetailsDB.computeIfAbsent(details.getUserID(), k -> new HashMap<>()).put(details.getYear(), details);
		return "Investment details for user ID " + details.getUserID() + " have been created successfully.";
	}

	@Override
	public InvestmentDetails getInvestmentDetails(int userID, int year) {
		return investmentDetailsDB.getOrDefault(userID, new HashMap<>()).get(year);
	}

	@Override
	public void generateTaxReport(InvestmentDetails details) {
		double income = details.getIncome();
		double licPremium = details.getLicPremium();
		double ppfInvestment = details.getPpfInvestment();
		double shortTermGains = details.getShortTermGains();
		double longTermGains = details.getLongTermGains();

		// Calculate deductions under Section 80C
		double section80C = licPremium + ppfInvestment;
		if (section80C > 150000) {
			section80C = 150000; // Maximum limit for Section 80C
		}

		// Calculate taxable income after deductions
		double taxableIncome = income - section80C;
		if (taxableIncome < 0) {
			taxableIncome = 0; // No negative taxable income
		}

		// Calculate tax based on taxable income
		double tax = calculateTax(taxableIncome, details.getTaxRegime());

		// Calculate capital gains tax
		double capitalGainsTax = calculateCapitalGainsTax(shortTermGains, longTermGains);

		// Generate report
		System.out.println("\n--- Tax Calculation Report ---");
		System.out.printf("Total Income: ₹%.2f%n", income);
		System.out.printf("LIC Premium: ₹%.2f%n", licPremium);
		System.out.printf("PPF Investment: ₹%.2f%n", ppfInvestment);
		System.out.printf("Section 80C Deduction: ₹%.2f%n", section80C);
		System.out.printf("Taxable Income: ₹%.2f%n", taxableIncome);
		System.out.printf("Income Tax: ₹%.2f%n", tax);
		System.out.printf("Short-term Capital Gains Tax: ₹%.2f%n", shortTermGains * 0.15);
		System.out.printf("Long-term Capital Gains Tax: ₹%.2f%n",
				(longTermGains > 100000 ? (longTermGains - 100000) * 0.10 : 0));
		System.out.printf("Total Tax Payable: ₹%.2f%n", tax + capitalGainsTax);
	}

	private double calculateTax(double income, String taxRegime) {
		double tax = 0;
		if ("Old".equalsIgnoreCase(taxRegime)) {
			if (income <= 250000) {
				tax = 0;
			} else if (income <= 500000) {
				tax = (income - 250000) * 0.05;
			} else if (income <= 1000000) {
				tax = 12500 + (income - 500000) * 0.20;
			} else {
				tax = 12500 + 100000 + (income - 1000000) * 0.30;
			}
		} else if ("New".equalsIgnoreCase(taxRegime)) {
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
		}
		return tax;
	}

	private double calculateCapitalGainsTax(double shortTermGains, double longTermGains) {
		double shortTermTax = shortTermGains * 0.15; // 15% tax on short-term gains
		double longTermTax = (longTermGains > 100000) ? (longTermGains - 100000) * 0.10 : 0; // 10% on gains above ₹1
																								// lakh
		return shortTermTax + longTermTax;
	}
}
