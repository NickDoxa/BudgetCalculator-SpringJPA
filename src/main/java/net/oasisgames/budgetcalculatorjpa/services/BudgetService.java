package net.oasisgames.budgetcalculatorjpa.services;

import lombok.Getter;
import net.oasisgames.budgetcalculatorjpa.BudgetCalculatorJpaApplication;
import net.oasisgames.budgetcalculatorjpa.components.BudgetInformation;
import net.oasisgames.budgetcalculatorjpa.components.Calculate;
import net.oasisgames.budgetcalculatorjpa.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for the Budget App logic
 */
@Getter
@Service
public class BudgetService {

    /**
     * -- GETTER --
     *  Gets the net.oasisgames.budgetcalculatorjpa.repository.BudgetRepository object
     */
    private BudgetRepository budgetRepository;

    /**
     * -- GETTER --
     *  Gets the net.oasisgames.budgetcalculatorjpa.components.Calculate object
     */
    private Calculate calculate;

    /**
     * Autowired net.oasisgames.budgetcalculatorjpa.components.Calculate object setter
     * @param calculate calculate object
     */
    @Autowired
    public void setCalculate(Calculate calculate) {
        this.calculate = calculate;
    }

    /**
     * Autowired net.oasisgames.budgetcalculatorjpa.repository.BudgetRepository object setter
     * @param budgetRepository net.oasisgames.budgetcalculatorjpa.repository.BudgetRepository object
     */
    @Autowired
    public void setBudgetRepository(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    /**
     * net.oasisgames.budgetcalculatorjpa.components.Calculate a budget report for the user and save their information using the repository
     * @param user the users name
     * @param income the users income
     * @param monthly the users monthly_expenses
     * @param weekly the users weekly_expenses
     * @return Budget Report as a String
     */
    public String calculateBudgetReportAndSave(String user, double income,
                                        double[] monthly, double[] weekly) {
        double remainder = calculate.calculateTotalRemainder(monthly, weekly, income);
        double tax_percent = calculate.calculateFederalTax(income);
        double taxes_taken = income * tax_percent;
        double expenses = calculate.calculateTotalExpenses(monthly, weekly);
        BudgetInformation info = BudgetCalculatorJpaApplication
                .getApplicationContext().getBean(BudgetInformation.class);
        info.setUsername(user);
        info.setRemainder(remainder);
        info.setTaxes(taxes_taken);
        info.setExpenses(expenses);
        budgetRepository.save(info);
        return "Your gross income was " + Calculate.formatToCurrency(income) +
                ". After losing " + Calculate.formatToCurrency(taxes_taken) +
                " to taxes, your income becomes " +
                Calculate.formatToCurrency(income - taxes_taken) +
                ". Your expenses add up to " + Calculate.formatToCurrency(expenses) +
                ". This leaves you with a final remainder of " +
                Calculate.formatToCurrency(remainder);
    }

}
