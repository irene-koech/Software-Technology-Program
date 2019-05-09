package lnu.models;

import java.awt.print.Book;
import javax.xml.bind.annotation.*;

@XmlRootElement(name ="book")
@XmlAccessorType(XmlAccessType.FIELD)
public class book {

	private String id;
	private String title;
	private String author;
	private String genre;
	private String publishDate;
	private String price;
	private String description;

public book(){
	id="";
	title="";
	author="";
	genre="";
	publishDate="";
	price="";
	description="";
}


	public book(String id, String title, String author, String genre, String publishDate, String price, String description) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publishDate = publishDate;
		this.price = price;
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public void setTitle(String title ) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor() {
		return author;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getGenre() {
		return genre;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublishDate() {
		return publishDate; }

	public void setPrice(String price) {
		this.price = price;
	}
	public String getPrice() {
		return price;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
  public String toString() {
		return "ID- " + id + " \n Title: " + title + "\n Author: " + author + "\n Genre" + genre +
				" \n Publsih Date: " + publishDate + " \n Price: " + price + " \n Description: " +description;
      }
    }
