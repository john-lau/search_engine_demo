package cs291a_hw1;

import static spark.Spark.*;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class WebService {
	
  public static void main(String[] args) {
	  SearchEngine engine = new SearchEngine();
	  
	  get("/", (req, res) -> 
		  "<!DOCTYPE HTML><html>" +
		  "<form action='/test'>" +
		  "<label for='query'>Search Query</label>" + 
		  "<input id='query' name='query' class='query-search' type='text'>" +
		  "<button type='submit' class='btn'>Search</button>" +
		  "</form></html>"
	  );
	  
	  get("/test", (req, res) -> {
		  String query = req.queryParams("query");
		  QueryResponse response = engine.searchQuery(engine.client, query, "title", 0);
		  
		  String q = query.split(":")[1];
		  
		  return displayQueryResponse(response, q);	 
	  });
  }
  
  public static String displayQueryResponse(QueryResponse response, String query) {
		SolrDocumentList results = response.getResults();
		String result = "<!DOCTYPE HTML><html>";
		for(int i = 0; i < results.size(); ++i) {
			result += "<p>" + results.get(i).toString() + "</p>\n";
		}
		result += "</html>";
		System.out.print(result);
		return highlightQuery(result, query);
	}
  
  public static String highlightQuery(String string, String query) {
	  String highlight = "<span style='background-color: #FFFF00'>" + query + "</span>";
	  return string.replaceAll(query, highlight);
  }
  
}
