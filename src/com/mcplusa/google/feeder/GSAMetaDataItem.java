/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcplusa.google.feeder;

/**
 *
 * @author mbcizmar
 */
public class GSAMetaDataItem {
    protected String name = "";
    protected String content = "";

    public GSAMetaDataItem()
    {

    }

    public GSAMetaDataItem(String newName, String newContent)
    {
        name = newName;
        content = newContent;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String newContent)
    {
        content = newContent;
    }

}
