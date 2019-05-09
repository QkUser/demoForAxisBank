package com.qk.axis.demoForAxisBank;

import java.io.InputStream;

import com.qk.axis.demoForAxisBank.BaseTestCase;

public class InputFileFinder {

  //private static final String DEFAULT_LANG_CODE = "Default"; //Internal constant

  public static InputStream getInputFileAsStream(BaseTestCase testClass) {
    return getInputFileAsStream(testClass.getClass());
  }

  public static InputStream getInputFileAsStream(Class<?> testClass) {
    // Check to see if we are in the default package; this happens often when we are running
    // from the Selenium Runner. If we are in the default package, null is returned
    // by the call to getPackage(). This must be handled correctly.
    String packageName = "";
    if (testClass.getPackage() != null) {
      packageName = testClass.getPackage().getName() + ".";
    }
    String resourceBase = (packageName + testClass.getSimpleName()).replace('.', '/');


    String resourceName;
    ClassLoader classLoader = testClass.getClassLoader();

   
      resourceName = resourceBase + ".xml";
      System.out.println("Searching for default input file: " + resourceName);
      return classLoader.getResourceAsStream(resourceName);
//    } else {
//      resourceName = resourceBase + "_" + inputLanguageCode + ".xml";
//      System.out.println("Searching for language input file: " + resourceName);
//      InputStream resourceStream = classLoader.getResourceAsStream(resourceName);
//      if (resourceStream == null) {
//        System.out.println("Input file not found!");
//        String oldCode = inputLanguageCode.replace("-", "_");
//        resourceName = resourceBase + "_" + oldCode + ".xml";
//        System.out.println("Searching for an alternative (old format) input file: " + resourceName);
//        resourceStream = classLoader.getResourceAsStream(resourceName);
////      }
//      return resourceStream;
//    }
  }

}
