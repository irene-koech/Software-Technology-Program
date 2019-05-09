package lnu.models;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonView;
import org.codehaus.jackson.map.DeserializationConfig;
//import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.annotation.*;
import java.io.IOException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.*;
import lnu.dao.booksDAO;
//@path("/books")
@XmlRootElement(name = "catalog")
@XmlAccessorType(XmlAccessType.FIELD)

public class catalog {
   @XmlElement(name = "book")
    private ArrayList<book> bookList;
    private static ObjectMapper mapper;

    public catalog() {
        bookList = new ArrayList<book>();
        catalog.mapper = new ObjectMapper();
    }
    public catalog (ArrayList<book> bookList,ObjectMapper mapper){
      this.bookList =bookList;
      catalog.mapper =mapper;
    }
    /**
     * @return the bookList
     */
    public ArrayList<book> getBookList() {
        return bookList;
    }
    public String stringToJson(){
      String jsonString ="";
      mapper = new ObjectMapper();
      try{
      jsonString += mapper.writeValueAsString(bookList);
    }
   catch (JsonGenerationException e) {
     e.printStackTrace();
   }
   catch (JsonMappingException e) {
     e.printStackTrace();
   }
 catch (IOException e) {
     e.printStackTrace();
   }
      return jsonString;
    }
    private book stringToBook(String stringIn) throws IOException{
    return catalog.mapper.readValue(stringIn, book.class);
  }
  @Override
  public String toString(){
    String str = "Book List: ";
    for(book books:bookList){
      str +="/n" + books.toString();
    }
    return str;
  }

  public void addBook(String bk) throws IOException{
    book newB = this.stringToBook(bk);
      newB.setId(this.bookExist());
      bookList.add(newB);
    }
    private String bookExist(){
      Collections.sort(bookList, new Comparator<book>() {
           @Override
           public int compare(book first, book second) {
               return Integer.parseInt(first.getId()) - Integer.parseInt(second.getId());
           }
       });
       return bookList.isEmpty() ? "1" : "" + (Integer.parseInt(bookList.get(bookList.size() - 1).getId()) + 1);
   }
   public void deleteBook(String id ) throws NotFoundException{
    bookList.remove(this.getBook(id));
     }
     private book getBook(String id) throws NotFoundException{
       for(int i= 0; i< bookList.size(); i++){
         if(bookList.get(i).equals(id)){
           return bookList.get(i);
         }
       }
       throw new NotFoundException();
     }
}
