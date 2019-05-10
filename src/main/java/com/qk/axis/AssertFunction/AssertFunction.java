package com.qk.axis.AssertFunction;

import java.io.IOException;

import org.springframework.util.Assert;

import com.qk.axis.demoForAxisBank.GenericFuction;

public class AssertFunction extends GenericFuction{

	public static boolean isElementPresent(boolean flag,String message) throws SecurityException, IOException, InterruptedException {
		
		try {
			
			Assert.isTrue(flag, message);
			return true;
		}catch(Exception e) {
			goToHome();
			return false;
		}
		
	
		
	}
	
}
