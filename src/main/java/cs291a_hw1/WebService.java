package cs291a_hw1;

import static spark.Spark.*;

public class WebService {
	
  public static void main(String[] args) {
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
		  return test(Integer.valueOf(query), 4);
	  });
  }
  
  public static int test(int a, int b) {
	  return a+b;
  }
  
  
}
