package com.mcplusa.google.feeder.constants;

/**
 *
 * @author mbcizmar
 */
public class ConnectorConstants {
  // mime
  public final static String MIME_TYPE = "mimetype";

  public final static String MIME_TEXT = "text/plain";

  // feeder
  public final static String FEEDER_LAST_MODIFIED = "last-modified";

  public final static String FEEDER_NAME = "name";

  public final static String FEEDER_CONTENT = "content";

  public final static String FEEDER_METADATA = "metadata";

  public final static String FEEDER_META = "meta";

  public final static String FEEDER_URL = "url";

  public final static String FEEDER_HEADER = "header";

  public final static String FEEDER_RECORD = "record";

  public final static String FEEDER_GROUP = "group";

  public final static String FEEDER_GSAFEED = "gsafeed";

  public final static String FEEDER_TYPE = "feedtype";

  public final static String FEEDER_DATA = "data";

  public final static String FEEDER_DS = "datasource";

  // feed types
  public final static String FEEDER_TYPE_FULL = "full";

  public final static String FEEDER_TYPE_INC = "incremental";

  public final static String FEEDER_TYPE_URL = "metadata-and-url";

  // authentication
  public final static String AUTH_TYPE_BASIC = "basic";
  public final static String AUTH_TYPE_NTLM = "ntlm";

  public static String utcFormat = "yyyy-MM-dd HH:mm:ss'Z'";

  public static String SLASH = "/";

  public static int MODE_FEED = 0;
  public static int MODE_QUERY = 1;
  public static String personalSpaceTag = "PersonalSpace",
      accountNameTag = "AccountName";

  public static String userProfileServiceAddress = "/_vti_bin/UserProfileService.asmx";
  public static String siteDataAddress = "/_vti_bin/SiteData.asmx";

  public static String TAG_ID = "ID";
  public static String TAG_MODIFIED = "Modified";
  public static String TAG_URL = "ServerUrl";
  //
  public static String SP_NAMESPACE = "http://schemas.microsoft.com/sharepoint/soap/";
  public static String SP_PREFIX = "SOAPSDK9";
  public static String SiteDirectoryTag = "SiteDirectory";
  // feeder
  public final static String FEEDER_GATE = ":19900/xmlfeed";

  public final static String FEEDER_DTD = ":7800/gsafeed.dtd";

  public static String _xmlDocType = "<!DOCTYPE gsafeed PUBLIC \"-//Google//DTD GSA Feeds//EN\"  \"http://gsa.mcplusa.com:7800/gsafeed.dtd\" >";
  public final static String FEEDER_DOCTYPESYS ="gsafeed.dtd";
  public final static String FEEDER_DOCTYPEPUB ="-//Google//DTD GSA Feeds//EN";
}
