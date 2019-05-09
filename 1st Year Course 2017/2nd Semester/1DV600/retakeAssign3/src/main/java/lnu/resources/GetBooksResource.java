package lnu.resources;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.JsonGenerationException;

import lnu.dao.booksDAO;
import lnu.models.book;
import lnu.models.catalog;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

// Response will be JSON
@Produces(MediaType.APPLICATION_JSON)

// This is the class that will be called when "localhost:9090/api/books" get called by the browser.
// FOR THE CURIOUS: Take a look in the config.yml to find out why "/api" is present in the path.
@Path("/books")
public class GetBooksResource {

private booksDAO books;
private catalog catalog;
public GetBooksResource(){
	books= new booksDAO();
}
public GetBooksResource(booksDAO books){
	this.books = books;
}
	@GET
	public String getBooks() throws Exception {
  catalog=books.jaxbXMLToObject();
	return catalog.stringToJson();
	}

}
