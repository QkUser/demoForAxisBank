package com.qk.axis.demoForAxisBank;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.DOMException;

import com.qk.axis.demoForAxisBank.TestDataSet;
import com.qk.axis.demoForAxisBank.TestDataSetBean;

public class InputFileDigester {

  private Document doc;

  private TestDataSet workingDataSet;

  /**
   * Builds a Document object from the specified InputStream
   *
   * @param in The InputStream to read the document from
 * @throws DocumentException 
   */
  public InputFileDigester(InputStream in) throws DocumentException {

	  
	  SAXReader reader = new SAXReader();
    try {
      doc = reader.read(in);
    } catch (DOMException e) {
      throw new RuntimeException("Error when attmeping to parse input file", e);
    }
  }

  public TestDataSet getWorkingDataSet() {
    return workingDataSet;
  }

  public List<TestDataSet> parseDataSets(String suiteDataSetName) {

    List<TestDataSet> dataSetCollection = new ArrayList<TestDataSet>();
    Element workingDset = (Element) this.doc.selectSingleNode("/testConfig/workingDataSet");

    String workingDsName = null;

    if (suiteDataSetName != null && (!suiteDataSetName.trim().isEmpty())) {
      workingDsName = suiteDataSetName.trim();
    } else {
      if (workingDset != null) {
        workingDsName = workingDset.attributeValue("name");
      }
    }

    System.out.println("====== Working Data Set: " + workingDsName);

    if (workingDsName != null) {
      Element datasets = (Element) this.doc.selectSingleNode("//datasets");

      for (Iterator<?> d = datasets.elementIterator("dataset"); d.hasNext();) {

        Element data = (Element) d.next();
        String dataSetName = data.attributeValue("name");
        TestDataSetBean dataSetBean = new TestDataSetBean(dataSetName);

        for (Iterator<?> e = data.elementIterator("var"); e.hasNext();) {
          Element elem = (Element) e.next();
          dataSetBean.addVar(elem.valueOf("@name"), elem.valueOf("."));
        }

        for (Iterator<?> e = data.elementIterator("file"); e.hasNext();) {
          Element elem = (Element) e.next();
          dataSetBean.addFile(elem.valueOf("@name"), elem.valueOf("."));
        }

        for (Iterator<?> e = data.elementIterator("list"); e.hasNext();) {
          Element elem = (Element) e.next();
          String listName = elem.attributeValue("name");

          ArrayList<String> varList = new ArrayList<String>();
          for (Iterator<?> l = elem.elementIterator("var"); l.hasNext();) {
            Element listElem = (Element) l.next();
            varList.add(listElem.getText());
          }
          if (varList.size() > 0) {
            dataSetBean.addVarList(listName, varList);
          } else {
            ArrayList<String> fileList = new ArrayList<String>();
            for (Iterator<?> l = elem.elementIterator("file"); l.hasNext();) {
              Element listElem = (Element) l.next();
              fileList.add(listElem.getText());
            }
            dataSetBean.addFiles(listName, fileList);
          }
        }
        TestDataSet dataSet = new TestDataSet(dataSetBean);
        dataSetCollection.add(dataSet);
        if (dataSetName.equals(workingDsName)) {
          this.workingDataSet = dataSet;
        }
      }
    } else {
      throw new RuntimeException("No Data Set Defined");
    }
    return dataSetCollection;
  }

}
