package com.qk.axis.demoForAxisBank;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;


public class BaseSolvent extends BaseTestCase{

	//static Logger logger = Logger.getLogger(GenericFunctions.class);
	//AppiumManager apm = new AppiumManager();
	WebDriver wdriver;

	private static Properties prop = new Properties();
//	private static File jarPath = new File(
//			ConnectionManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//	private static String propertiesPath = jarPath.getParentFile().getAbsolutePath();
	public static AppiumDriverLocalService appiumDriverLocalService;
	public AppiumServiceBuilder builder = new AppiumServiceBuilder();
	static AppiumDriver objAppiumDriver = null;
	public static int port = 0;

	public static AppiumDriver createAndroidDriverInstance() throws Exception {

		
		

		try {
			appiumServerForAndroid();
//			logger.info("Appium Server Started...");
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Iphone");
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"12.1.2");
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Ios");
			dc.setCapability(MobileCapabilityType.UDID,"fba3433f23f4e0745fc8a78fb520b912d43728ee");
			dc.setCapability(MobileCapabilityType.NO_RESET, true);
			dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
			dc.setCapability("bundleId", "com.axisbank.axismobileuat");
			objAppiumDriver = new AppiumDriver(new URL("http://0.0.0.0:" + port + "/wd/hub"), dc);
			//logger.info("Application Launched....");
			System.out.println("Application Launched....");

			// autoOnboarding(objAppiumDriver, webStatus, deviceDetails);
		} catch (Exception objDriverException) {
			objDriverException.printStackTrace();
			//logger.error("Exception occurred in While Launching app...." + objDriverException.getMessage());
		}
		return objAppiumDriver;
	}

	public static AppiumServiceBuilder appiumServerForAndroid() throws Exception {

		Properties prop = new Properties();

	
		// port = 5743;
		port = getPort();
		System.out.println(port);
		int chromePort = getPort();
		// int chromePort = ap.getPort();
		int bootstrapPort = getPort();
		// int bootstrapPort = 6433;
		/*
		 * port = 4723; int chromePort = 5723; int bootstrapPort = 6723;
		 */

		System.out.println(prop.getProperty("APPIUM_JS"));

		AppiumServiceBuilder builder = new AppiumServiceBuilder().withAppiumJS(new File(prop.getProperty("APPIUM_JS")))
				.usingDriverExecutable(new File(prop.getProperty("APPIUM_NODE")))
				.withArgument(GeneralServerFlag.LOG_LEVEL, "info")
				.withArgument(AndroidServerFlag.CHROME_DRIVER_PORT, Integer.toString(chromePort))
				.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, Integer.toString(bootstrapPort))
				.withIPAddress(prop.getProperty("0.0.0.0")).withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				.withArgument(AndroidServerFlag.SUPPRESS_ADB_KILL_SERVER).usingPort(port);

		appiumDriverLocalService = builder.build();
		appiumDriverLocalService.start();
		return builder;
	}

	public static int getPort() throws Exception {
		ServerSocket socket = new ServerSocket(0);
		socket.setReuseAddress(true);
		int port = socket.getLocalPort();
		socket.close();
		return port;

	}
}
