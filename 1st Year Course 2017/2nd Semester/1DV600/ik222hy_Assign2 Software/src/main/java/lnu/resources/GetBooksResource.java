package lnu.resources;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lnu.dao.booksDAO;
import lnu.models.book;
import lnu.models.catalog;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

// Response will be JSON
@Produces(MediaType.APPLICATION_JSON)

// This is the class that will be called when "localhost:9090/api/books" get called by the browser.
// FOR THE CURIOUS: Take a look in the config.yml to find out why "/api" is present in the path.
@Path("/books")
public class GetBooksResource {
	//private File file = new File("src/main/java/lnu/dao/books.xml");

	@GET
	public String getBooks() {

	ObjectMapper mapper = new ObjectMapper();

	try{
		    catalog cata ;
	    	booksDAO book = new booksDAO();
		    cata = book.jaxbXMLToObject();
		    return mapper.writeValueAsString(cata.getBookList());
	}catch(Exception e) {

	    e.printStackTrace();
 	}
	return "";
	}
}
