package com.qk.axis.demoForAxisBank;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qk.axis.FT.FundTransferFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class GenericFuction extends BaseSolvent {
	static AppiumDriver<?> driver = null;

	public static void logIn() throws SecurityException, IOException, InterruptedException {
		{

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Login")))
					.isDisplayed();
			// tc.screenshotcapture(driver, scenarioName, new
			// Object(){}.getClass().getEnclosingMethod().getName(), executionDetails,
			// "SUCCESS");
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Login"))).click();
			try {
				// Search bar xpath
				new WebDriverWait(driver, 2).until(
						ExpectedConditions.visibilityOfElementLocated(MobileBy.className("XCUIElementTypeSearchField")))
						.click();
			} catch (Exception e) {
			}
		}

	}

	public static AppiumDriver openApp() {

		try {
			driver = createAndroidDriverInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FundTransferFunctions.driver = driver;
		return driver;
	}

	public void EnterMpin(String mpin) throws Exception {
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.presenceOfElementLocated(By.className("XCUIElementTypeTextField")))
				.isDisplayed();
		driver.findElement(By.className("XCUIElementTypeTextField")).sendKeys(mpin);
	}

	public void NotAvailableThenSwipeUp(String xpath) throws InterruptedException {
		for (int i = 0; i <= 10; i++) {
			try {
				boolean cityDisplay = driver.findElement(By.xpath("" + xpath + "")).isDisplayed();
				if (cityDisplay == true) {
					// select city
					driver.findElement(By.xpath(xpath)).click();
					break;
				} else {
					Dimension size = driver.manage().window().getSize();

					int startx = (int) (size.width * 0.50);

					int Endx = (int) (size.width * 0.50);

					int starty = (int) (size.height * 0.80);

					int Endy = (int) (size.height * 0.20);

					scrolling(startx, starty, 1000, Endx, Endy);
				}
			} catch (Exception e) {

				Dimension size = driver.manage().window().getSize();

				int startx = (int) (size.width * 0.50);

				int Endx = (int) (size.width * 0.50);

				int starty = (int) (size.width * 0.80);

				int Endy = (int) (size.width * 0.20);

				scrolling(startx, Endx, 1000, starty, Endy);

			}
		}

	}

	public void scrolling(int startx, int starty, int duration, int endx, int endy) throws InterruptedException {
		// logger.info("Scrolling Start...");
		(new TouchAction(driver)).press(PointOption.point(startx, starty))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endx, endy))
				.release().perform();
		// logger.info("Scrolling End...");
	}

	public static void goToHome() throws SecurityException, IOException, InterruptedException {
		try {
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("OK"))).click();
		} catch (Exception e) {
		}

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//XCUIElementTypeImage)[3]")))
				.isEnabled();
		driver.findElement(By.xpath("(//XCUIElementTypeImage)[3]")).click();

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("Home"))).isDisplayed();

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("Home"))).click();
	}

}
