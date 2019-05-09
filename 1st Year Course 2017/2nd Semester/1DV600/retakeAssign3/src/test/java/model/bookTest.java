package model;
import static org.junit.Assert.*;
import lnu.models.*;
import org.junit.Before;
import org.junit.Test;
import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

public class bookTest {
	    private book nwBook;
	    private String id ="12";
	    private String author= "Irene";
	    private String title= "TestingBook" ;
	    private String genre = "iok";
	    private String price = "100";
	    private String publish_date = "26032018";
	    private String description = "Learnigmore";
	    private String nwBkToString = "ID- " + id + " Title: " + title + " Author: " + author + " Genre" + genre +
					" Publsih Date: " + publish_date + " Price: " + price + " Description: " +description;
	@Before
	    public void setBook(){
	    nwBook = new book();
	}
	@Test
	    public void setAndGetId(){
	    nwBook.setId(id);
	    assertEquals(nwBook.getId(),id);
	}
	@Test
	    public void setAndGetAuthor(){
	    nwBook.setAuthor(author);
	    assertEquals(nwBook.getAuthor(), author);
	}
	@Test
	    public void setAndGetTitle(){
	    nwBook.setTitle(title);
	    assertEquals(nwBook.getTitle(), title);
	}
	@Test
	    public void setAndGetGenre(){
	    nwBook.setGenre(genre);
	    assertEquals(nwBook.getGenre(),genre);
	}
	@Test
	    public void setAndGetPrice(){
	    nwBook.setPrice(price);
	    assertEquals(nwBook.getPrice(),price);
	}
	@Test
	    public void setAndGetPublish_date(){
	    nwBook.setPublish_date(publish_date);
	    assertEquals(nwBook.getPublish_date(), publish_date);
	}
	@Test
	    public void setAndDescription(){
	    nwBook.setDescription(description);
	    assertEquals(nwBook.getDescription(), description);
	}
	@Test
	    public void setAndGetString(){
	    nwBook.setId(id);
	    nwBook.setAuthor(author);
	    nwBook.setTitle(title);
	    nwBook.setGenre(genre);
	    nwBook.setPrice(price);
	    nwBook.setPublish_date(publish_date);
	    nwBook.setDescription(description);

	    assertEquals(nwBook.toString(),nwBkToString);
	}

}
