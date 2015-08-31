package com.mcplusa.google.feeder.uploader;

import com.mcplusa.google.feeder.constants.*;
import com.mcplusa.google.feeder.FeederUtil;

import org.apache.log4j.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author mbcizmar
 */
public class GSAUploader {
    //protected logger;
    protected boolean performLog;
    protected int webtimeout = 10*60*1000; //10 minutes
    protected Logger logger;

    public GSAUploader() {

    }

    public GSAUploader(Logger myLogger){
        performLog = true;
        logger = myLogger;
    }

    private boolean PostToGSA(String gsaUrl, Part[] data, String docUrl) {
       logger.info("PostToGSA starting");
       HttpClient client = new HttpClient();
       PostMethod post = new PostMethod(gsaUrl + ConnectorConstants.FEEDER_GATE);
       
       post.setRequestEntity(new MultipartRequestEntity(data, post.getParams()));

        // execute method and handle any error responses.
        try {
            client.executeMethod(post);
            if (post.getStatusCode() == HttpStatus.SC_OK) {
                post.releaseConnection();
                logger.info("Post response: SC_OK");
            } else {
                logger.error(post.getStatusLine().toString());
                logger.error(post.getResponseBodyAsString());
                post.releaseConnection();
            }
        } catch (IOException e) {
            logger.error("Exception", e);
            return false;
        }
        logger.info("PostToGSA ending");
        return true;
    }

    public boolean FeedXml(String sourceUrl, String gsaHost, boolean ssl) throws ParserConfigurationException {
        logger.info("FeedXml starting");
        String gsaFeedUrlPrimary ="";
        String feedtype = "";
        String datasource = "";

        if (ssl)
        {
            gsaFeedUrlPrimary = "https://" + gsaHost;
            logger.info("GSA Url set to: " + gsaFeedUrlPrimary);
        } else
        {
            gsaFeedUrlPrimary = "http://" + gsaHost;
            logger.info("GSA Url set to: " + gsaFeedUrlPrimary);
        }
        
        String xmlString;
        try
        {
            xmlString = FeederUtil.readFileAsString(sourceUrl);
            logger.info("Read file as string");
        } catch (IOException e)
        {
            logger.error("File error", e);
            return false;
        }

        Document doc;
        try {
            doc = FeederUtil.readStringAsDocument(xmlString);
            logger.info("xml document read");
        } catch (SAXParseException ex) {
             logger.error("Unable to parse document " + sourceUrl + " at line " + ex.getLineNumber() + " at column " + ex.getColumnNumber());
            logger.error(ex);
            return false;
        } catch (SAXException ex) {
            logger.error("Unable to parse document ",ex);
            return false;
        } catch (IOException ex) {
            logger.error("File IO Error", ex);
            return false;
        }

        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            feedtype = xpath.evaluate("/gsafeed/header/feedtype", doc);
            logger.info("Feedtype is: " + feedtype);
            datasource = xpath.evaluate("/gsafeed/header/datasource",doc);
            logger.info("datasource is: " + datasource);
        } catch (XPathExpressionException ex) {
            logger.error("XPath Error", ex);
            return false;
        }


        try {
            Part[] parts = {
            new StringPart(ConnectorConstants.FEEDER_TYPE, feedtype),
            new StringPart(ConnectorConstants.FEEDER_DS, datasource),
            new StringPart(ConnectorConstants.FEEDER_DATA, xmlString)};
            PostToGSA(gsaFeedUrlPrimary,parts,sourceUrl);
            logger.info("data posted");

        
      } catch (Exception ex) {
        logger.error("Exception thrown",ex);
        return false;
      }
      logger.info("FeedXML ending");
      return true;
    }
}
