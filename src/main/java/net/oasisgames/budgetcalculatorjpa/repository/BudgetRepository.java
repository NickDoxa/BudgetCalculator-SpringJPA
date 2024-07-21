package net.oasisgames.budgetcalculatorjpa.repository;

import net.oasisgames.budgetcalculatorjpa.components.BudgetInformation;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Budget Repository class for the Budget Calculator App
 */
@Repository
@Scope("singleton")
public interface BudgetRepository extends JpaRepository<BudgetInformation, String> {

    /**
     * Finds all usernames currently in the data table
     * @return String list of all usernames in the data table
     */
    @Query("select username from BudgetInformation")
    List<String> findAllUsernames();
}
