package com.qk.axis.fundtransfer2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qk.axis.FT.FundTransferFunctions;

public class FundTransferTest extends FundTransferFunctions{
	
	static boolean appOpen = false ;
	static boolean loggedIn = false ;
	String souldLogout = getData().getVariable("souldLogout");
	String Nickname = getData().getVariable("NickName");
	String Mpin = getData().getVariable("Mpin");
  @Test
  public void f() throws Exception {
	  
	  System.out.println(Nickname);
		
		try {
		if(driver.findElement(By.xpath("(//XCUIElementTypeImage)[3]")).isDisplayed()) {
			goToHome();
		}}catch(Exception e) {
		
		if (!appOpen) {
			openApp();
			appOpen = true;
		}if (!loggedIn) {
			logIn();
			EnterMpin(Mpin);
			loggedIn=true;}
		}
			
			clickOnFundTransferButton().
			clickOnUpcomingTab().
			clickOnInitialButtonOfPayee(Nickname).
			clickOnFooterPayButton();
			
			if (souldLogout.contains("true")) {
				appOpen = false;
				loggedIn = false;
			} else {
				// goToHome();
			}
 
}
}
