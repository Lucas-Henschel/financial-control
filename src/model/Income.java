/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.IncomeCategory;
import enums.LaunchType;
import java.time.LocalDateTime;

/**
 *
 * @author nponcio
 */
public class Income extends Launch {
    private IncomeCategory incomeCategory;
	
    public Income() {
        super(null, 0, LaunchType.INCOME);
    }
	
    public Income(LocalDateTime dateTime, double amount, IncomeCategory incomeCategory) {
	super(dateTime, amount, LaunchType.INCOME);
	this.incomeCategory = incomeCategory;
    }
	
    public IncomeCategory getIncomeCategory() {
	return incomeCategory;
    } 

    public void setIncomeCategory(IncomeCategory incomeCategory) {
	if (incomeCategory == null || incomeCategory == IncomeCategory.DEFAULT) {
            throw new IllegalArgumentException("Categoria deve ser informada");
        }
	
        this.incomeCategory = incomeCategory;
    }
}
