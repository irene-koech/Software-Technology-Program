package lnu.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lnu.models.book;
import java.util.ArrayList;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
// Response will be JSON
@Produces(MediaType.APPLICATION_JSON)

// This is the class that will be called when "localhost:9090/api/books" get called by the browser.
// FOR THE CURIOUS: Take a look in the config.yml to find out why "/api" is present in the path.
@Path("/books")
public class GetBooksResource {

	@GET
	public String getBooks()throws Exception {
		book b=new book("bk101","Gambardella, Matthew","XML Developer's Guide","Computer","2000-10-01","44.95","An in-depth look at creating applications with XML.");
		book b1 =new book("bk102","Ralls, Kim","Midnight Rain","Fantasy","5.95","2000-12-16","A former architect battles corporate zombies an evil sorceress, and her own childhood to queen of the world.");

	ArrayList<book> books = new ArrayList<book>();
	 books.add(b);
	 books.add(b1);
	 ObjectMapper mapper = new ObjectMapper();
  //  String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(books);
 //   assertTrue(json.contains(b.getName()));
  //  assertTrue(json.contains(b1.getName()));

	String jsonInString = mapper.writeValueAsString(books);
			System.out.println(jsonInString);
  // System.out.println(json);
		//System.out.println(b);
			//return b.toString;
			//System.out.println(b1);
				return jsonInString;

	}

}
