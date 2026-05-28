package com.example.spring_boot_slack_integration;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Demonstrates TestNG dependency ordering and soft-assertion style patterns.
 * Group: "smoke" — intended as a quick pre-flight check.
 */
public class SmokeTest {

    // Initialized at field level — reliable across all TestNG/Surefire combos
    private CalculatorService calculator = new CalculatorService();

    @BeforeMethod
    public void init() {
        calculator = new CalculatorService();
        System.out.println("[SETUP] SmokeTest.init called, calculator=" + calculator);
    }

    @Test(groups = "smoke", priority = 1, description = "Service instantiation sanity check")
    public void testServiceNotNull() {
        Assert.assertNotNull(calculator, "CalculatorService should not be null");
    }

    @Test(groups = "smoke", priority = 2, dependsOnMethods = "testServiceNotNull",
          description = "Basic add smoke test")
    public void testBasicAddSmoke() {
        Assert.assertEquals(calculator.add(1, 1), 2);
    }

    @Test(groups = "smoke", priority = 3, dependsOnMethods = "testBasicAddSmoke",
          description = "Basic divide smoke test")
    public void testBasicDivideSmoke() {
        Assert.assertEquals(calculator.divide(6, 2), 3.0);
    }
}

