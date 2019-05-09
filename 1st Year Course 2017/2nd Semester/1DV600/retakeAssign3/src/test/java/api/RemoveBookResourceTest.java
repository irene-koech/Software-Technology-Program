
import lnu.dao.booksDAO;
import lnu.models.*;
import lnu.resources.RemoveBookResource;

import org.junit.Test;
import org.junit.Before;
import org.junit.ClassRule;
import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import static io.dropwizard.testing.FixtureHelpers.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RemoveBookResourceTest{
  private static booksDAO mockDao= mock(booksDAO.class);
  private catalog mockCatalog = mock(catalog.class);
  private static RemoveBookResource test = new RemoveBookResource(mockDao);

  @ClassRule
  public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(test).build();

 @Before
 public void intialSetUp() throws Exception{
   when(mockDao.jaxbXMLToObject()).thenReturn(mockCatalog);
 }

  @Test
  public void shouldRemoveBook() throws Exception{
    String id = "1";
   when(mockDao.jaxbXMLToObject()).thenReturn(mockCatalog);
    doNothing().when(mockCatalog).deleteBook(id);
    doNothing().when(mockDao).objectToJaxbXML(mockCatalog);
    assertThat(resources.client().target("/books/" + id).request().delete().getStatus()).isEqualTo(200);
    }
    @Test
    public void shouldThrowException() throws Exception{
        String id = "1";
        when(mockDao.jaxbXMLToObject()).thenReturn(mockCatalog);
        doThrow(new IOException()).when(mockCatalog).deleteBook(id);

        assertThat(resources.client().target("/books/" + id).request().delete()
                .getStatus()).isEqualTo(404);
    }

}
