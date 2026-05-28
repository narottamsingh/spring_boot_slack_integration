package com.example.spring_boot_slack_integration;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit tests for CalculatorService using TestNG.
 * Groups: "math", "validation"
 * Demonstrates: basic assertions, DataProvider, expected exceptions.
 * Note: plain unit test — no Spring context needed, so @SpringBootTest is omitted.
 */
public class CalculatorServiceTest {

    // Initialized at field level — reliable across all TestNG/Surefire combos
    private CalculatorService calculator = new CalculatorService();

    @BeforeMethod
    public void setUp() {
        // Re-instantiate before each method for clean state
        calculator = new CalculatorService();
        System.out.println("[SETUP] CalculatorServiceTest.setUp called, calculator=" + calculator);
    }

    // ── Basic math ────────────────────────────────────────────────────────────

    @Test(groups = "math", description = "Addition of two positive integers")
    public void testAdd() {
        Assert.assertEquals(calculator.add(3, 7), 10, "3 + 7 should equal 10");
    }

    @Test(groups = "math", description = "Subtraction result can be negative")
    public void testSubtract() {
        Assert.assertEquals(calculator.subtract(5, 9), -4);
    }

    @Test(groups = "math", description = "Multiplication by zero always returns zero")
    public void testMultiplyByZero() {
        Assert.assertEquals(calculator.multiply(999, 0), 0);
    }

    @Test(groups = "math", description = "Division returns a double")
    public void testDivide() {
        Assert.assertEquals(calculator.divide(10, 4), 2.5);
    }

    @Test(groups = "math",
          expectedExceptions = IllegalArgumentException.class,
          description = "Divide by zero must throw IllegalArgumentException")
    public void testDivideByZero() {
        calculator.divide(1, 0);
    }

    // ── Parameterised tests via @DataProvider ─────────────────────────────────

    @DataProvider(name = "additionData")
    public Object[][] additionData() {
        return new Object[][] {
            {1,  2,  3},
            {0,  0,  0},
            {-5, 5,  0},
            {10, 20, 30},
        };
    }

    @Test(groups = "math",
          dataProvider = "additionData",
          description = "Parameterised addition test")
    public void testAddParameterised(int a, int b, int expected) {
        Assert.assertEquals(calculator.add(a, b), expected,
                String.format("%d + %d should be %d", a, b, expected));
    }

    // ── Palindrome validation ─────────────────────────────────────────────────

    @DataProvider(name = "palindromeData")
    public Object[][] palindromeData() {
        return new Object[][] {
            {"racecar",       true},
            {"hello",         false},
            {"A man a plan a canal Panama", true},
            {"",              true},
            {null,            false},
        };
    }

    @Test(groups = "validation",
          dataProvider = "palindromeData",
          description = "Palindrome check with multiple inputs")
    public void testIsPalindrome(String input, boolean expected) {
        Assert.assertEquals(calculator.isPalindrome(input), expected,
                "Palindrome check failed for: " + input);
    }

    // ── Negative / edge cases ─────────────────────────────────────────────────

    @Test(groups = "math", description = "Multiply two negatives gives positive")
    public void testMultiplyNegatives() {
        Assert.assertTrue(calculator.multiply(-3, -4) > 0);
    }
}

