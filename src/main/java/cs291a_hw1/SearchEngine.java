package cs291a_hw1;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

public class SearchEngine {
	
	SolrClient client;
	
	public SearchEngine() {
		this.client = new HttpSolrClient.Builder("http://localhost:8983/solr/trec45").build();
	}
	
	
	public QueryResponse searchQuery(SolrClient client, int start, String userquery) throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
		query.setQuery(userquery);
		query.setStart(start);			
		return client.query(query);
	}

}
