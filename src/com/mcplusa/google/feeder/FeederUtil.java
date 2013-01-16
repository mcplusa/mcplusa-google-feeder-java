/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcplusa.google.feeder;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.mcplusa.google.feeder.constants.ConnectorConstants;
import org.xml.sax.SAXParseException;

/**
 *
 * @author mbcizmar
 */
public class FeederUtil {

    /** @param filePath the name of the file to open. Not sure if it can accept URLs or just filenames. Path handling could be better, and buffer sizes are hardcoded
    */
    public static String readFileAsString(String filePath)
    throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            fileData.append(buf, 0, numRead);
        }
        reader.close();
        return fileData.toString();
    }

    public static Document createDocument()
    {
        Document document = null;
		try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	        document = documentBuilder.newDocument();
		}
		catch(ParserConfigurationException e)
		{
			//Todo: add logging
		}
		return document;

    }

    public static Document readStringAsDocument(String tempXml)
            throws SAXParseException,SAXException,IOException,ParserConfigurationException
    {
        DocumentBuilder parser;
        try {
            parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw ex;
        }
        Document doc;
        try {
            java.io.StringReader stringReader = new java.io.StringReader(tempXml);
            org.xml.sax.InputSource iSource = new org.xml.sax.InputSource(stringReader);
            doc = parser.parse(iSource);
        } catch (SAXParseException ex) {
            throw ex;
        } catch (SAXException ex) {
            throw ex;
        } catch (IOException ex) {
            throw ex;
        }

        return doc;
    }

    public static Transformer createTransformer(String gsa)
	{
		Transformer transformer = null;
		try{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.DOCTYPE_SYSTEM, ConnectorConstants.FEEDER_DOCTYPESYS);
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.DOCTYPE_PUBLIC,ConnectorConstants.FEEDER_DOCTYPEPUB);
		}
		catch(TransformerConfigurationException e)
		{
			//Todo
		}
        return transformer;
	}

    //to save the output to file
	public static void writeToFile(Transformer transformer, Document document, String fileName)
	{
		try{
			StreamResult result =  new StreamResult(fileName);
			DOMSource source = new DOMSource(document);
			transformer.transform(source, result);
		}
		catch(TransformerException e)
		{
			//Todo
		}
	}
}
