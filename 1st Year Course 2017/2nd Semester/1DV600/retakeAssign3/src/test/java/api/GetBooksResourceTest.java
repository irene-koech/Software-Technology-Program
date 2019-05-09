
import lnu.models.*;
import lnu.dao.booksDAO;
import lnu.resources.GetBooksResource;

import org.junit.Test;
import org.junit.Before;
import org.junit.ClassRule;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import static io.dropwizard.testing.FixtureHelpers.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GetBooksResourceTest{
  private static booksDAO mockDao = mock(booksDAO.class);
  private catalog mockCatalog = mock(catalog.class);
  private static GetBooksResource test = new GetBooksResource(mockDao);
  @ClassRule
  public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(test).build();

  @Test
  public void showDisplayBooks() throws Exception{
    String book1 = "{\"id\":\"1\",\"author\":\"Horstmann\",\"title\":\"Java\",\"genre\":\"Friction\",\"price\":\"800\",\"publish_date\":\"2008\",\"description\":\"Eagerly anticipated by millions of programmers, Java SE 8 is the most important Java update in many years. The addition of lambda expressions (closures) and streams represents the biggest change to Java programming since the introduction of generics and annotations.\"}";
    when(mockDao.jaxbXMLToObject()).thenReturn(mockCatalog);
    when(mockCatalog.stringToJson()).thenReturn(book1);
    assertEquals(book1, resources.client().target("/books/").request().get(String.class));
  }
}
