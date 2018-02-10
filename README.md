# search engine demo

**you should have solr-7.2.1 downloaded to run this**

How to Run:
---------------
To start up solr (you should be in the directory solr-7.2.1):
-run the command "bin/solr start" for single solr
For SolrCloud:
- for the leader node, run the command "bin/solr start -c -p 8100 -s example/cloud/node1/solr"
- for node 2 and 3, run the command "bin/solr start -c -p 8200 -z localhost:9100 -s example/cloud/node2/solr" and "bin/solr start -c -p 8300 -z localhost:9100 -s example/cloud/node3/solr"

Once Solr is running, you can compile the code and run WebService.java
- By default it is set up to run single solr. If you want to change this, change engine.solrClient to engine.solrCloudClient in the line where we call engine.searchQuery

**you should start up both single solr and SolrCloud before running WebService.java because searchEngine creates both clients by default**

visit localhost:4567 to search. Query times should be printed out to your terminal / console.
