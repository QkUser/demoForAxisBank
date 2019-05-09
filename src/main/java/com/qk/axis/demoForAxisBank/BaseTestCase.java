package com.qk.axis.demoForAxisBank;



import java.io.InputStream;
import java.util.Date;

/**
 * Base tests class for all CSLM products using the Base Selenium Framework
 *
 * @author dodhekar
 *
 */
public abstract class BaseTestCase   {



  private static int uniquifySequence;
  private boolean isDataSetInitialized = false;

  private static TestDataSet data;



  public static String uniquify(String prefix) {
    StringBuilder key = new StringBuilder();

    if (!prefix.isEmpty()) {
      key.append(prefix);
      if (!prefix.endsWith("_")) {
        key.append('-');
      }
    }

    // add a counter to the milli timestamp, in case a bunch of keys are generated at once
    long currentMillis = new Date().getTime();
    currentMillis += (++uniquifySequence);
    key.append(currentMillis);

    return key.toString();
  }


  private void initializeDataSet() {
    if (!isDataSetInitialized) { // if data set is not initialized
      try {
        // Load in parameters from the input file for this test.
        isDataSetInitialized = true;
        // Read in any input parameters, if an input file is found
        InputStream in = InputFileFinder.getInputFileAsStream(this);
        if (in != null) { // Found an input file for this test
          System.out.println("Found test input...digesting file...");
          InputFileDigester digester = new InputFileDigester(in);
          digester.parseDataSets(null);
          data = digester.getWorkingDataSet();
        } else {
          System.out.println("No test input file found");
        }
      } catch (Exception e) {
        System.out
            .println("Error parsing input file.. this will most likely cause the test to fail");
        e.printStackTrace();
      }
    }
  }

  /**
   * Returns a dataset that has been parsed, if any.
   *
   * @throws SolventException if no dataset has been found/parsed
   * @return
   */
  public TestDataSet getData() {
    initializeDataSet();
    if (data == null) {
      throw new RuntimeException(
          "Cannot retrieve dataset. No dataset was found for this test class.");
    } else {
      return data;
    }
  }

}
