package com.qk.axis.demoForAxisBank;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

  private static final String notFound = "\" not found";

  private TestDataSetBean myBean;

  public TestDataSet(TestDataSetBean bean) {
    this.myBean = bean;
  }

  /**
   * Retrieves the name for this dataset.
   *
   * @return The value defined for the specified variable
   */
  public String getName() {
    return myBean.getName();
  }

  /**
   * Retrieves the value defined for a variable.Throws invalid parameter exception if no matching
   * variable found.
   *
   * @param varName Name of the variable to retrieve
   * @return The value defined for the specified variable
   */
  public String getVariable(String varName) {
    String varValue = myBean.getVarValue(varName);
    if (varValue == null) {
      throw new RuntimeException("Input variable \"" + varName + notFound);
    }
    return varValue;
  }

  /**
   * Retrieves list of values defined for a variable. Throws invalid parameter exception if no
   * matching variable found,.
   *
   * @param listName Name of the list to retrieve
   * @return List of values defined for the specified variable
   */
  public List<String> getVariables(String listName) {
    List<String> vars = myBean.getVarValues(listName);
    if (vars == null) {
      throw new RuntimeException("Input variable list \"" + listName + notFound);
    }
    return vars;
  }

  /**
   * Retrieves the file defined for a file variable. Throws invalid parameter exception if no
   * matching variable found. Throws invalid file exception if no file exists with specified file
   * path
   *
   * @param fileName Name of the file to retrieve
   * @return file defined for the specified fileVariable
   */
  public File getFile(String fileName) {
    String filePath = myBean.getFile(fileName);
    if (filePath == null) {
      throw new RuntimeException("Input file variable \"" + fileName + notFound);
    }
    File file = new File(filePath);
    if (!file.exists()) {
      throw new RuntimeException("Input file \"" + filePath + notFound);
    }
    return file;
  }

  /**
   * Retrieves the list of files defined for a file variable. Throws invalid parameter exception if
   * no matching file variable found. Throws invalid file exception if no file exists with specified
   * file path
   *
   * @param listName Name of the file to retrieve
   * @return list of files defined for the specified file variable
   */
  public List<File> getFiles(String listName) {
    List<String> filePaths = myBean.getFiles(listName);
    if (filePaths == null) {
      throw new RuntimeException("Input list variable \"" + listName + notFound);
    }
    List<File> files = new ArrayList<File>();
    for (String filePath : filePaths) {
      File file = new File(filePath);
      if (!file.exists()) {
        throw new RuntimeException("Input file \"" + filePath + notFound);
      }
      files.add(file);
    }
    return files;
  }
}
