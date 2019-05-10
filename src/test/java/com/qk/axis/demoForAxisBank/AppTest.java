package com.qk.axis.demoForAxisBank;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qk.axis.AssertFunction.AssertFunction;
import com.qk.axis.FT.FundTransferFunctions;

import io.appium.java_client.MobileBy;

public class AppTest extends FundTransferFunctions {

	String Nickname = getData().getVariable("NickName");
	String Mpin = getData().getVariable("Mpin");
	String souldLogout = getData().getVariable("souldLogout");
	static boolean appOpen = false;
	static boolean loggedIn = false;
	AssertFunction asse = new AssertFunction();

	@Test
	public void f() throws Exception {
		System.out.println(Nickname);
		try {
			if (new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//XCUIElementTypeImage)[3]"))).isDisplayed()){
				goToHome();
			}
		} catch (Exception e) {

			if (!appOpen) {
				openApp();
				appOpen = true;
			}
			if (!loggedIn) {
				logIn();
				EnterMpin(Mpin);
				loggedIn = true;
			}

		}
		clickOnFundTransferButton().clickOnUpcomingTab().clickOnInitialButtonOfPayee(Nickname).clickOnFooterPayButton();
		asse.isElementPresent(new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("PAY NOW"))).isDisplayed(),
				"Pay now button not displayed");
		if (souldLogout.contains("true")) {
			appOpen = false;
			loggedIn = false;
		} else {
			// goToHome();
		}
	}
}
