package net.oasisgames.budgetcalculatorjpa.controllers;

import net.oasisgames.budgetcalculatorjpa.components.BudgetInformation;
import net.oasisgames.budgetcalculatorjpa.repository.BudgetRepository;
import net.oasisgames.budgetcalculatorjpa.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for the Budget App
 */
@RestController
public class BudgetController {

    private BudgetService budgetService;
    private BudgetRepository budgetRepository;

    /**
     * Post Mapping for the budget path
     * @param user the users name
     * @param income the users income
     * @param monthly_expenses the users monthly expenses
     * @param weekly_expenses the users weekly expenses
     * @return Budget report in JSON format
     */
    @PostMapping("budget")
    public BudgetReport sendBudgetData(String user, double income,
                               double monthly_expenses, double weekly_expenses) {
        return new BudgetReport(budgetService.calculateBudgetReportAndSave(
                user, income, new double[] {monthly_expenses}, new double[] {weekly_expenses}));
    }

    /**
     * Get Mapping for the budget path
     * @param user the users name
     * @return Budget information in JSON format
    */
    @GetMapping("budget")
    public BudgetInformation getBudgetData(String user) {
        return budgetRepository.findById(user).orElse(null);
    }

    /**
     * Get Mapping for the users path
     * @return Users list in JSON format
     */
    @GetMapping("users")
    public UserList getAllUsers() {
        return new UserList(budgetService.getBudgetRepository().findAllUsernames());
    }

    /**
     * Autowired setter for the BudgetService object
     * @param budgetService the budget service object (Autowired)
     */
    @Autowired
    public void setBudgetService(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    /**
     * Autowired setter for the BudgetRepository object
     * @param budgetRepository the BudgetRepository object (Autowired)
     */
    @Autowired
    public void setBudgetRepository(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    /**
     * Static inner class created for the purpose of returning JSON data instead of a raw
     * String report
     */
    public static class BudgetReport {

        public String report;

        public BudgetReport(String report) {
            this.report = report;
        }
    }

    /**
     * Static inner class created for the purpose of returning JSON data instead of a raw
     * string list of users
     */
    public static class UserList {

        public List<String> users;

        public UserList(List<String> users) {
            this.users = users;
        }

    }

}
