package com.mcplusa.google.feeder;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.mcplusa.google.feeder.constants.ConnectorConstants;

public class GSAGroupsFeed {
  protected Logger logger;
  protected Document doc;
  protected Node root;
  protected int count = 0;

  public int getCount() {
    return count;
  }

  public void setCount(int newCount) {
    count = newCount;
  }
  
  public void setLogger(Logger newLogger) {
    logger = newLogger;
  }

  public GSAGroupsFeed() {
    doc = FeederUtil.createDocument();
  }
  
  public void BuildHeader() throws SAXException, IOException {
    logger.info("Starting BuildHeader");
    try {
      root = doc.createElement(ConnectorConstants.FEEDER_XMLGROUP);
      doc.appendChild(root);

    } catch (Exception ex) {
      // Parser with specified options can't be built
      logger.error("Exception in BuildHeader");
      logger.error(ex.getMessage());
    }
    logger.info("Ending BuildHeader");
    return;
  } 
  
  public void AddGroupMembership(GSAGroupMembership group) {
    this.AddGroupMembership(group, root);
  }
  
  private void AddGroupMembership(GSAGroupMembership group, Node groupContainer) {
    Element membershipNode = doc.createElement("membership");
    
    GSAGroupPrincipal principal = group.getPrincipal();
    Element principalNode = doc.createElement("principal");
    principalNode.setAttribute("namespace", principal.getNamespace());
    principalNode.setAttribute("case-sensitivity-type", principal.getCaseSensitivityType().getValue());
    principalNode.setAttribute("scope", principal.getScope().getValue());
    principalNode.appendChild(doc.createTextNode(principal.getContent()));
    membershipNode.appendChild(principalNode);

    Element membersNode = doc.createElement("members");
    for (GSAGroupPrincipal member: group.getMembers()) {
      Element memberNode = doc.createElement("principal");
      memberNode.setAttribute("namespace", member.getNamespace());
      memberNode.setAttribute("case-sensitivity-type", member.getCaseSensitivityType().getValue());
      memberNode.setAttribute("scope", member.getScope().getValue());
      memberNode.appendChild(doc.createTextNode(member.getContent()));
    }
    membershipNode.appendChild(membersNode);
    
    groupContainer.appendChild(membershipNode);
  }
}
