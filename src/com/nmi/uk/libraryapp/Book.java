package com.nmi.uk.libraryapp;

/**
 * Created by Darren on 23/08/2014.
 */
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(namespace = "com.nmi.uk.sw.Library")
public class Book implements Serializable {

    private String title;
    private String author;
    private String category;

    public Book() {

    }

    public Book(String title, String author, String category) {
        super();
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
