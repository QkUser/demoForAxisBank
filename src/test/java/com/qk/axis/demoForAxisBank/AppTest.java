package com.qk.axis.demoForAxisBank;

import org.testng.annotations.Test;

import com.qk.axis.FT.FundTransferFunctions;

public class AppTest extends FundTransferFunctions {
	String Nickname = getData().getVariable("NickName");
	String Mpin = getData().getVariable("Mpin");

	@Test
	public void f() {
		System.out.println(Nickname);
		try {
			openApp();
			logIn();
			EnterMpin(Mpin);
			clickOnFundTransferButton().
			clickOnUpcomingTab().
			clickOnInitialButtonOfPayee(Nickname).
			clickOnFooterPayButton();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
