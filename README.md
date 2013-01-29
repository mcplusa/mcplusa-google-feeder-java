MC+A Google Java Feeder
============================

A port of the MC+A C# Library for developing simple feeders to the Google Search Appliance into Java.

# Usage
 
```
 log.info("Begin delete");
 String feedFile = "";
 GSAUploader uploader = new GSAUploader(log);
 GSAFeed myFeed = null;
 
 log.debug("Setting up empty full feed");
 myFeed = new GSAFeed(log, this.feedLocation, GSAFeedType.FEEDTYPE_FULL);
 myFeed.setDataSource(this.dataSource);
 myFeed.BuildHeader();
 
 feedFile = myFeed.WriteXMLToFile(gsa);
 
 log.info("temp xml file saved to: " + feedFile);
 uploader.FeedXml(feedFile, gsa, false);
 log.info("item submitted to gsa");
 
  uploader = null;
  myFeed = null;
```

# Dependencies
* log4j

# Related Projects
[GSALib C# SDK for Query GSA](http://gsalib.codeplex.com)
[MC+A C# SDK for GSA](http://github.com/mcplusa/mcplusa-google-feeder-csharp/)

Copyright 2013 Michael Cizmar + Associates Ltd. ([MC+A](http://www.mcplusa.com/))

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
