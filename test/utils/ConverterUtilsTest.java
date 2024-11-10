/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package utils;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicolle
 */
public class ConverterUtilsTest {

    @Test
    public void testWithCommaAndDot() {
        String[] amountsText = {"10,90", "10.90"};
        List<Double> amounts = new ArrayList<>();

        for (String amountText : amountsText) {
            amounts.add(ConverterUtils.convertToAmount(amountText));
        }

        List<Double> amountExpected = List.of(10.90, 10.90);

        assertEquals(amountExpected, amounts);
    }

    @Test
    public void testWhenIsLargeAmount() {
        String amountText = "111222333444555,99";

        double amount = ConverterUtils.convertToAmount(amountText);

        assertEquals(111222333444555.99, amount, 0);
    }

    @Test
    public void testConvertToAmount() {
        String amountText = "10";

        double amount = ConverterUtils.convertToAmount(amountText);

        assertEquals(10, amount, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWith16DigitsBeforeDecimal() {
        String amountText = "1112223334445556,99";

        double amount = ConverterUtils.convertToAmount(amountText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWith3DigitsAfterDecimal() {
        String amountText = "10,123";

        double amount = ConverterUtils.convertToAmount(amountText);
    }

    @Test(expected = NumberFormatException.class)
    public void testWithInvalidCharacters() {
        String amountText = "*-94^^a";

        double amount = ConverterUtils.convertToAmount(amountText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithNegativeValue() {
        String amountText = "-1";

        double amount = ConverterUtils.convertToAmount(amountText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithZero() {
        String amountText = "0";

        double amount = ConverterUtils.convertToAmount(amountText);
    }

    @Test
    public void testConvertToLocalDateTime() {
        String dateText = "01/01/2024";
        LocalDateTime resultDateTime = ConverterUtils.convertToLocalDateTime(dateText);

        LocalDateTime expectedDateTime = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0);

        assertEquals(expectedDateTime.getDayOfMonth(), resultDateTime.getDayOfMonth());
        assertEquals(expectedDateTime.getMonth(), resultDateTime.getMonth());
        assertEquals(expectedDateTime.getYear(), resultDateTime.getYear());
    }

    @Test
    public void testConvertToLocalDateTimeWithMinDate() {
        String dateText = "01/01/1900";
        LocalDateTime resultDateTime = ConverterUtils.convertToLocalDateTime(dateText);

        LocalDateTime expectedDateTime = LocalDateTime.of(1900, Month.JANUARY, 1, 0, 0);

        assertEquals(expectedDateTime.getDayOfMonth(), resultDateTime.getDayOfMonth());
        assertEquals(expectedDateTime.getMonth(), resultDateTime.getMonth());
        assertEquals(expectedDateTime.getYear(), resultDateTime.getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToLocalDateTimeWithDateBeforeMinDate() {
        String dateText = "31/12/1899";
        LocalDateTime resultDateTime = ConverterUtils.convertToLocalDateTime(dateText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToLocalDateTimeWithInvalidDay() {
        String dateText = "32/01/2024";
        LocalDateTime resultDateTime = ConverterUtils.convertToLocalDateTime(dateText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToLocalDateTimeWithInvalidMonth() {
        String dateText = "01/13/2024";
        LocalDateTime resultDateTime = ConverterUtils.convertToLocalDateTime(dateText);
    }
    
    @Test
    public void testParseIsoDateTime() {
        String dateTimeText = "2024-01-01T00:00:00.000000000";
        
        LocalDateTime resultDateTime = ConverterUtils.parseIsoDateTime(dateTimeText);
        
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0);
        assertEquals(expectedDateTime, resultDateTime);
    }
    
    @Test(expected = DateTimeParseException.class)
    public void testParseIsoDateTimeWithInvalidMonth() {
        String dateTimeText = "2024-34-01T00:00:00.000000000";
        ConverterUtils.parseIsoDateTime(dateTimeText);
    }
    
    @Test(expected = DateTimeParseException.class)
    public void testParseIsoDateTimeWithInvalidDay() {
        String dateTimeText = "2024-01-32T00:00:00.000000000";
        ConverterUtils.parseIsoDateTime(dateTimeText);
    }

    @Test
    public void testValidCategoryWithOtherIncome() {
        ConverterUtils.validCategory(IncomeCategory.OTHER_INCOME, null);
    }

    @Test
    public void testValidCategoryWithOtherExpense() {
        ConverterUtils.validCategory(null, ExpenseCategory.OTHER_EXPENSE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidCategoryWithDefaultIncome() {
        ConverterUtils.validCategory(IncomeCategory.DEFAULT, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidCategoryWithDefaultExpense() {
        ConverterUtils.validCategory(null, ExpenseCategory.DEFAULT);
    }

    @Test
    public void testFormatToCurrency() {
        double amount = 0;
        String amountText = ConverterUtils.formatToCurrency(amount);

        assertEquals("R$ 0,00", amountText);
    }

    @Test
    public void testFormatToDate() {
        LocalDateTime dateTime = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0);
        String dateString = ConverterUtils.formatToDate(dateTime);

        assertEquals("01/01/2024", dateString);
    }
}
