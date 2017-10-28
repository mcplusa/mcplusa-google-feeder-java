package com.mcplusa.google.feeder;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import javax.xml.transform.*;

import org.w3c.dom.*;
import org.apache.xml.serialize.XMLSerializer;

import com.mcplusa.google.feeder.constants.ConnectorConstants;

import org.xml.sax.SAXException;


/**
 *
 * @author mbcizmar
 */
public class GSAFeed {
  protected Logger logger;
  protected Document doc;
  protected Node root;
  protected Node groupNode;  //node containing all record nodes (content)
  protected String feederFolder = "";
  protected String dataSource = "";
  protected String feederType = "";
  protected int count = 0;

  public int getCount() {
    return count;
  }

  public void setCount(int newCount) {
    count = newCount;
  }

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String newDataSource) {
    dataSource = newDataSource;
  }

  public void setLogger(Logger newLogger) {
    logger = newLogger;
  }

  public GSAFeed() {
    doc = FeederUtil.createDocument();
  }

  public GSAFeed(Logger newLogger, String xmlFileFolder, String newFeedType) {
    logger = newLogger;
    feederFolder = xmlFileFolder;
    feederType = newFeedType;
    doc = FeederUtil.createDocument();

    logger.info("Feed temp folder will be: " + xmlFileFolder);
  }

  public void BuildHeader() throws SAXException, IOException {
    logger.info("Starting BuildHeader");
    try {
      //Element header = doc.createElement(ConnectorConstants.FEEDER_HEADER);

      root = doc.createElement(ConnectorConstants.FEEDER_GSAFEED);
      doc.appendChild(root);
      Element header = doc.createElement("header");
      Element dataSourceElement = doc.createElement(ConnectorConstants.FEEDER_DS);
      dataSourceElement.appendChild(doc.createTextNode(dataSource));
      header.appendChild(dataSourceElement);

      Element feedTypeElement = doc.createElement(ConnectorConstants.FEEDER_TYPE);
      feedTypeElement.appendChild(doc.createTextNode(feederType));
      header.appendChild(feedTypeElement);
      root.appendChild(header);


      //Create group element
      groupNode = doc.createElement(ConnectorConstants.FEEDER_GROUP);
      root.appendChild(groupNode);

    } catch (Exception ex) {
      // Parser with specified options can't be built
      logger.error("Exception in BuildHeader");
      logger.error(ex.getMessage());
    }
    logger.info("Ending BuildHeader");
    return;
  }

  public final String WriteXMLToFile(String gsa) {
    logger.info("Starting WriteXmlToFile");
    Date now = new Date();
    SimpleDateFormat sd = new SimpleDateFormat("yyyy_mm_dd_hh_ss");
    
    String timestamp = sd.format(now);
    
    String filename = this.dataSource + "_" +  timestamp + ".xml";
    try {
    	if (feederFolder.length() > 0) {
    		if (feederFolder.endsWith("\\")) {
    			filename = feederFolder + filename;
    		} else {
    			filename = feederFolder + "\\" + filename;
    		}
    	} else {
    		//do nothing there was no specified folder
    	}
        // use specific Xerces class to write DOM-data to a file:
        XMLSerializer serializer = new XMLSerializer();
        Transformer transformer = FeederUtil.createTransformer(gsa);
        FeederUtil.writeToFile(transformer, doc, filename);
  
    } catch (Throwable ex) {
        logger.error("Error  GSAFeed.WriteXMLToFile()", ex);
    }
    logger.info("File written to: " + filename + " successfully.");
    logger.info("Ending WriteXmlToFile");
    return filename;
  }

  public void DeleteRecord(String url) {
      try {
          Element recordNode = doc.createElement("record");
          recordNode.setAttribute("url", url);
          recordNode.setAttribute("action", "delete");

          //insert recordNode into GroupNode
          groupNode.appendChild(recordNode);
          //insert groupNode into document
          count++;
      } catch (Exception ex) {
          logger.error("Error  XmlGSAFeed.DeleteRecord()", ex);

      }
  }

  public void AddRecord(GSAContentItem item) {
    Element recordNode = doc.createElement("record");
    recordNode.setAttribute("url", item.getUrl());
    if (item.display_url.length() > 0) {
    	recordNode.setAttribute("displayurl", item.display_url);
    }
    recordNode.setAttribute("mimetype", item.getMimeType());
    if (item.getHasLastModified()) {
      recordNode.setAttribute("last-modified", item.getLastModifiedUTC());
    }

    recordNode.setAttribute("authmethod", item.getAuthMethod());
    if (item.getAcl() != null) {
      this.AddACL(item.getAcl(), recordNode, true);
    }
    if (item.metaData.size() > 0) {
      Element metaData = doc.createElement("metadata");
      GSAMetaDataItem metaItem;
      for (int i = 0; i < item.getMetadata().size(); i++) {
        metaItem = (GSAMetaDataItem) item.getMetadata().get(i);
        if ((metaItem.getName().length() > 0) && (metaItem.getContent() != null)) {
          Element meta = doc.createElement("meta");
          meta.setAttribute("name", metaItem.getName());
          meta.setAttribute("content", metaItem.getContent());
          metaData.appendChild(meta);
        }
      }
      recordNode.appendChild(metaData);
    }

    if (item.content.length() > 0) {
      //assume content feed and add the current  node
      Element content = doc.createElement("content");
      if (item.getBase64()) {
        content.setAttribute("encoding", "base64binary");
      }
      content.setTextContent(item.getContent());
      recordNode.appendChild(content);
    }
    
    groupNode.appendChild(recordNode);
    count++;

  }

  public void AddACL(GSAACLItem item) {
    this.AddACL(item, groupNode, false);
  }
    
  private void AddACL(GSAACLItem item, Node aclContainer, boolean aclInsideRecord) {
    Element aclNode = doc.createElement("acl");
    if (!aclInsideRecord) {
      aclNode.setAttribute("url", item.getUrl());
    }
    
    if (item.getInheritanceType() != null) {
      aclNode.setAttribute("inheritance-type", item.getInheritanceType().getValue());
    }
    
    if (item.getInheritFrom() != null && item.getInheritFrom().trim().length() != 0) {
      aclNode.setAttribute("inherit-from", item.getInheritFrom());
    }
    
    if (item.getPrincipals().size() > 0) {
      for (GSAACLPrincipal principalItem : item.getPrincipals()) {
        Element principal = doc.createElement("principal");
        principal.setAttribute("namespace", principalItem.getNamespace());
        principal.setAttribute("case-sensitivity-type", principalItem.getCaseSensitivityType().getValue());
        principal.setAttribute("scope", principalItem.getScope().getValue());
        principal.setAttribute("access", principalItem.getAccess().getValue());
        principal.appendChild(doc.createTextNode(principalItem.getContent()));
        aclNode.appendChild(principal);
      }
    }
    aclContainer.appendChild(aclNode);
  }
 
}