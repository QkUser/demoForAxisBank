package com.qk.axis.demoForAxisBank;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPHeader;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;

public class ReadDataFromXml {
	static Document doc;
public static void main(String [] args) {
	

	try {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		 doc = builder.parse("/Users/quality/eclipse-workspace/demo/src/test/java/demo/demo.xml");
		String mobilenumber=getname("MobileNumber");
		System.out.println(mobilenumber);
		
		
		
		
	} catch (SAXException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	
	
	
}
			
	
	public static String getname(String nodename){
		NodeList list = doc.getElementsByTagName(nodename);
		Node node = list.item(0);
		Element e = (Element)node;
		return e.getTextContent();
		
	}
	
//	  public static InputStream getInputFileAsStream(BaseTestCase testClass) {
//		    return getInputFileAsStream(testClass.getClass());
//		  }

}
