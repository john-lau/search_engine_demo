package cs291a_hw1;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DistributedDocumentAdder {
	
	private static int flush = 0; //so we can periodically flush
	
	public static void main(String[] args) throws IOException, SolrServerException {
  
	    String zkHost = "localhost:9100";
	    CloudSolrClient client = new CloudSolrClient.Builder().withZkHost(zkHost).build();
	    client.setDefaultCollection("trec45_distributed");
	    client.connect();
//	    CloudSolrClient client = new CloudSolrClient.Builder().withSolrUrl("http://localhost:8000/solr").build();
//	    client.setDefaultCollection("trec45_distributed");
	    String file = "data/lines-trec45.txt";
	    addDocuments(client, file);
	    client.commit(); 
	  }

	private static void addDoc(SolrClient client, String docid, String title, String body) throws IOException, SolrServerException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("docid", docid);
        doc.addField("title", title);
        doc.addField("body", body);
        flush++;
        client.add(doc);
        if(flush%100 == 0) client.commit();
    }
    
    private static void addDocuments(SolrClient client, String fileName) throws IOException {
    	Files.lines(Paths.get(fileName))
    		.forEach(line->{
    			String[] s = line.split("\\t", 3);
				try {
					addDoc(client, s[0], s[1], s[2]);
				} catch (IOException | SolrServerException e) {
					e.printStackTrace();
				}
			});
	}

}
