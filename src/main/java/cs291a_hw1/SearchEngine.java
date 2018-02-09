package cs291a_hw1;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

public class SearchEngine {
	
	SolrClient client;
	long startTime;
	
	public SearchEngine() {
		this.client = new HttpSolrClient.Builder("http://localhost:8983/solr/trec45").build();
//	    String zkHost = "localhost:9100";
//	    CloudSolrClient client = new CloudSolrClient.Builder().withZkHost(zkHost).build();
//	    client.setDefaultCollection("trec45_distributed");
//	    client.connect();
//	    this.client=client;
//	    System.out.println("client successfully created!");
	}
	
	
	public QueryResponse searchQuery(SolrClient client, int start, String userquery) throws SolrServerException, IOException {
		startTime = System.currentTimeMillis();
		System.out.println("startTime established");
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery(userquery);
			query.setStart(start);			
			System.out.println("query is all set up!");
			return client.query(query);
		} catch (SolrServerException e) {
			System.out.println(e.toString());
			return null;
		}
	}

}
