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
//		  return test(Integer.valueOf(query), 4);
		  System.out.print("query is: " + query);
		  QueryResponse response = engine.searchQuery(engine.client, query, "title", 0);
		  return displayQueryResponse(response);
		  
	  });
  }
  
  public static String displayQueryResponse(QueryResponse response) {
		SolrDocumentList results = response.getResults();
		String result = "<!DOCTYPE HTML><html>";
		for(int i = 0; i < results.size(); ++i) {
//			System.out.print(results.get(i).toString());
			result += "<p>" + results.get(i).toString() + "</p>\n";
		}
		result += "</html>";
		return result;
	}
  
  public static int test(int a, int b) {
	  return a+b;
  }
  
  
}
