package lnu.resources;
import lnu.dao.*;
import lnu.models.*;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import java.util.List;
import java.io.IOException;

@Consumes(MediaType.APPLICATION_JSON)
@Path("/books")
public class AddBookResource {
  private booksDAO addBook;

public AddBookResource(){
  addBook =new booksDAO();

}
public AddBookResource(booksDAO bookTest){
  this.addBook = bookTest;

}
@PUT
@Consumes(MediaType.APPLICATION_JSON)
public Response addBook(String book1) throws Exception{
  try{
    catalog addCata= addBook.jaxbXMLToObject();
    addCata.addBook(book1);
    addBook.objectToJaxbXML(addCata);
    return Response.ok().build();

  }catch(IOException e){
    return Response.status(Response.Status.NOT_FOUND).build();
  }
}
}
