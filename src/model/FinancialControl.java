/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class FinancialControl {
    private static PersistenceCSVImpl persistenceCSVImpl = PersistenceCSVImpl.getPersistenceCSVImpl();
    
    /**
     * Cria as receitas com base nos parametros recebidos
     * 
     * @param amount
     * @param incomeCategory
     * @param dateTime
     * @throws IOException
     */
    public static void createIncome(double amount, IncomeCategory incomeCategory, LocalDateTime dateTime) throws IOException, IllegalArgumentException {
        Income income = new Income();
        income.setDateTime(dateTime);
        income.setAmount(amount);
        income.setIncomeCategory(incomeCategory);
        
        persistenceCSVImpl.saveRegister(income, null);
    }
    
    /**
     * Cria as despesas com base nos parametros recebidos
     * 
     * @param amount
     * @param expenseCategory
     * @param dateTime 
     * @throws IOException
     */
    public static void createExpense(double amount, ExpenseCategory expenseCategory, LocalDateTime dateTime) throws IOException, IllegalArgumentException { 
        Expense expense = new Expense();
        expense.setDateTime(dateTime);
        expense.setAmount(amount);
        expense.setExpenseCategory(expenseCategory);
        
        persistenceCSVImpl.saveRegister(null, expense);
    }
    
    /**
     * Lista todas as receitas armazenadas
     * @return 
     */
    public static List<Income> listIncome() {
        List<Income> listIncome = new ArrayList<>();
        
        return listIncome;
    }
    
    /**
     * Lista todas as despesas armazenadas
     * @return 
     */
    public static List<Expense> listExpense() {
        List<Expense> listExpense = new ArrayList<>();
        
        return listExpense;
    }
}
