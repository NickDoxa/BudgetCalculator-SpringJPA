package net.oasisgames.budgetcalculatorjpa.components;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Getter
@Setter
@Data
public class BudgetInformation {

    @Id
    private String username;
    private double remainder;
    private double expenses;
    private double taxes;

}
