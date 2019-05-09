package com.qk.axis.demoForAxisBank;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class TestDataSetBean {

  private Map<String, String> myVars = new Hashtable<String, String>();

  private Map<String, List<String>> myVarLists = new Hashtable<String, List<String>>();

  private Map<String, String> myFiles = new Hashtable<String, String>();

  private Map<String, List<String>> myFileLists = new Hashtable<String, List<String>>();

  private String name;

  public TestDataSetBean(String name) {
    this.name = name;
  }

  public void addVar(String varName, String varVal) {
    myVars.put(varName, varVal);
  }

  public void addVarList(String varName, List<String> valueList) {
    myVarLists.put(varName, valueList);
  }

  public void addFile(String fileName, String filePath) {
    myFiles.put(fileName, filePath);
  }

  public void addFiles(String fileName, List<String> fileList) {
    myFileLists.put(fileName, fileList);
  }

  public String getName() {
    return this.name;
  }

  public String getVarValue(String varName) {
    if (myVars.containsKey(varName)) {
      return myVars.get(varName);
    }
    return null;
  }

  public List<String> getVarValues(String listName) {
    if (myVarLists.containsKey(listName)) {
      return myVarLists.get(listName);
    }
    return null;
  }

  public String getFile(String fileName) {
    if (myFiles.containsKey(fileName)) {
      return myFiles.get(fileName);
    }
    return null;
  }

  public List<String> getFiles(String listName) {
    if (myFileLists.containsKey(listName)) {
      return myFileLists.get(listName);
    }
    return null;
  }

}
