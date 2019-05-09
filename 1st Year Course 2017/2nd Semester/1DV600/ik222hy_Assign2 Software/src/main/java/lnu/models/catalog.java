package lnu.models;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;
import io.dropwizard.jackson.Jackson;
import com.fasterxml.jackson.databind.ObjectMapper;

@XmlRootElement(name = "catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class catalog {

   @XmlElement(name = "book")
    private ArrayList<book> bookList;
    private static ObjectMapper mapper;

    public catalog() {
        bookList = new ArrayList<book>();
    }
    /**
     * @return the bookList
     */
    public ArrayList<book> getBookList() {
        return bookList;
    }

}
