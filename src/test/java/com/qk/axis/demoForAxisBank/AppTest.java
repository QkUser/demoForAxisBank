package com.qk.axis.demoForAxisBank;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qk.axis.FT.FundTransferFunctions;

public class AppTest extends FundTransferFunctions {
	String Nickname = getData().getVariable("NickName");
	String Mpin = getData().getVariable("Mpin");
	static boolean flag = false;

	@Test
	public void f() throws Exception {
		System.out.println(Nickname);
		if (!flag) {
			openApp();
		flag = true;
		}else {
			logIn();}
		EnterMpin(Mpin);
		clickOnFundTransferButton().clickOnUpcomingTab().clickOnInitialButtonOfPayee(Nickname).clickOnFooterPayButton();

		Assert.assertTrue(driver.findElementByAccessibilityId("PAY NOW").isDisplayed(), "Pay now button not displayed");

	}
}
