/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcplusa.google.feeder;

import java.util.*;
import com.mcplusa.google.feeder.constants.*;

/**
 *
 * @author mbcizmar
 */
public class GSAContentItem {

    protected String url = "";
    protected String display_url = "";
	protected String mimetype = "";
	protected String content = "";
	protected boolean base64 = false;
    protected boolean lastModifiedSet = false;
	protected Date	lastModified;
	protected ArrayList   metaData;
	protected String authMethod = GSAAuthMethodTypes.AUTH_METHOD_NONE;

    public GSAContentItem()
	{
		metaData = new ArrayList();
	}

    public String getUrl()
    {
        return url;
    }
    
    public void setDisplayUrl(String newDisplayUrl) {
    	display_url = newDisplayUrl;
    }

    public void setUrl(String newUrl)
    {
        url = newUrl;
    }

    public String getMimeType()
    {
        return mimetype;
    }

    public void setMimeType(String newMimeType)
    {
        
        mimetype = newMimeType;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String newContent)
    {
        content = newContent;
    }

    public boolean getBase64()
    {
        return base64;
    }

    public void setBase64(boolean newBase64)
    {
        base64 = newBase64;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date newDate)
    {
        lastModifiedSet = true;
        lastModified = newDate;
    }

    public String getLastModifiedUTC()
    {
        return lastModified.toString();

    }

    public boolean getHasLastModified()
    {
        return lastModifiedSet;
    }

    public ArrayList getMetadata()
    {
        return metaData;
    }

    public String getAuthMethod()
    {
        return authMethod;
    }

    public void setAuthmethod(String newAuthMethod)
    {
        authMethod = newAuthMethod;
    }

    public void addMetaData(String name, String content)
    {
        if (content.isEmpty()) {
            //do nothing
        } else {
            //add
            GSAMetaDataItem item = new GSAMetaDataItem(name,content);
            metaData.add(item);
        }
        
    }

}
