// Use this file to write and read the xml file.
// http://www.journaldev.com/1234/jaxb-tutorial-example-to-convert-object-to-xml-and-xml-to-object
// javax.xml.bind is added as a part of the sdk from java7 and forward.
package lnu.dao;
import javax.xml.bind.annotation.*;
import javax.xml.bind.*;
import java.io.File;
import lnu.models.*;
import java.io.IOException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBContext;

public class booksDAO {
	private catalog catalog;
	private File file = new File("books.xml");
	public booksDAO() {}

		public booksDAO(File file){
			this.file=file;
		}
	/**
	 * Read XML file and return catalog object
	 */
	public catalog jaxbXMLToObject() throws Exception {
		JAXBContext context = JAXBContext.newInstance(catalog.class);
		Unmarshaller un = context.createUnmarshaller();
		return (catalog) un.unmarshal(file);
	}
	public void objectToJaxbXML(catalog catalog) throws Exception{
		JAXBContext context = JAXBContext.newInstance(catalog.class);
		Marshaller mars = context.createMarshaller();
		mars.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
		mars.marshal(catalog,file);
	}
}
