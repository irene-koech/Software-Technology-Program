package lnu.models;

import java.awt.print.Book;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name ="book")
//@XmlAccessorType(XmlAccessType.FIELD)
public class book {
	// TODO - add id mapping from books.xml
	private String id;
	private String title;
	private String author;
	private String genre;
	private String publish_date;
	private String price;
	private String description;

public book(){
	id="";
	title="";
	author="";
	genre="";
	publish_date="";
	price="";
	description="";
}


	public book(String id, String title, String author, String genre, String publish_date, String price, String description) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publish_date = publish_date;
		this.price = price;
		this.description = description;
	}
@XmlAttribute
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

	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getPublish_date() {
		return publish_date; }

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
		return "ID- " + id + " Title: " + title + " Author: " + author + " Genre" + genre +
				" Publsih Date: " + publish_date + " Price: " + price + " Description: " +description;
      }
    }
