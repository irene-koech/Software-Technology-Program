package lnu.resources;
import lnu.models.catalog;
import lnu.dao.booksDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Produces(MediaType.APPLICATION_JSON)
@Path("/books")
public class RemoveBookResource {
  private booksDAO removeBook;

  public RemoveBookResource(){
    removeBook = new booksDAO();
  }
  public RemoveBookResource(booksDAO RemoveBook){
    removeBook = RemoveBook;
  }
  @DELETE
  @Path("{book_id}")
  public Response deleteBook(@PathParam("book_id") String id) throws Exception{
    try {
      catalog removeBk = removeBook.jaxbXMLToObject();
      removeBk.deleteBook(id);
      removeBook.objectToJaxbXML(removeBk);
      return Response.ok().build();

    }catch(IOException e){
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

}
