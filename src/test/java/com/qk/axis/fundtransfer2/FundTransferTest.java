package com.qk.axis.fundtransfer2;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qk.axis.FT.FundTransferFunctions;

public class FundTransferTest extends FundTransferFunctions{
	static boolean flag = false ;
	String Nickname = getData().getVariable("NickName");
	String Mpin = getData().getVariable("Mpin");
  @Test
  public void f() throws Exception {
	  
	  System.out.println(Nickname);
		
		if (!flag) {
			openApp();
		flag = true;
		}else {
			logIn();}
			EnterMpin(Mpin);
			clickOnFundTransferButton().
			clickOnUpcomingTab().
			clickOnInitialButtonOfPayee(Nickname).
			clickOnFooterPayButton();
			
 
}
}
