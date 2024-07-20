package net.oasisgames.budgetcalculatorjpa.repository;

import net.oasisgames.budgetcalculatorjpa.components.BudgetInformation;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope("singleton")
public interface BudgetRepository extends JpaRepository<BudgetInformation, String> {

    List<BudgetInformation> findAllByUsername(String userId);
    @Query("select username from BudgetInformation")
    List<String> findAllUsernames();
}
