/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Nicolle
 */
public class ConverterUtils {
    private static final LocalDateTime MIN_DATE = LocalDateTime.of(1900, Month.JANUARY, 1, 0, 0);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public static double convertToAmount(String amount) throws IllegalArgumentException {
        if (isValidAmount(amount)) {
            return Double.parseDouble(amount.replace(",", "."));
        }
        
        throw new IllegalArgumentException("Por favor, informe um valor válido");
    }
    
    public static LocalDateTime convertToLocalDateTime(String date) {
        LocalDateTime localDateTime;
        try {
            LocalDate localDate = LocalDate.parse(date, formatter);
            localDateTime = localDate.atTime(LocalTime.now());
            
            if (!isValidDateTime(localDateTime)) {
                throw new IllegalArgumentException("Por favor, informe uma data válida");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Por favor, informe uma data válida");
        }
        
        return localDateTime;
    }
    
    public static void validCategory(IncomeCategory incCat, ExpenseCategory expCat) {
        if ((incCat == null || incCat == IncomeCategory.DEFAULT) && (expCat == null || expCat == ExpenseCategory.DEFAULT)) {
            throw new IllegalArgumentException("Por favor, selecione o tipo da transação");
        }
    }
    
    private static boolean isValidAmount(String amountText) {
        return amountText != null && amountText.matches("\\d+(\\.\\d{1,2})?");
    }
    
    private static boolean isValidDateTime(LocalDateTime dateTime) {
        return dateTime != null && !dateTime.isBefore(MIN_DATE);
    }
}
