package com.qk.axis.fundtransfer2;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qk.axis.FT.FundTransferFunctions;

public class FundTransferTest extends FundTransferFunctions{
	String Nickname = getData().getVariable("NickName");
	String Mpin = getData().getVariable("Mpin");
  @Test
  public void f() throws Exception {
	  
	  System.out.println(Nickname);
		
			openApp();
			logIn();
			EnterMpin(Mpin);
			clickOnFundTransferButton().
			clickOnUpcomingTab().
			clickOnInitialButtonOfPayee(Nickname).
			clickOnFooterPayButton();
			
 
}
}
