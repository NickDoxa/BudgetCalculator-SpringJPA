package net.oasisgames.budgetcalculatorjpa;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BudgetCalculatorJpaApplication {

    @Getter
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(BudgetCalculatorJpaApplication.class, args);
    }

}
