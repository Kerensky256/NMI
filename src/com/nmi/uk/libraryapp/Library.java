package com.nmi.uk.libraryapp;

/**
 * Created by Darren on 23/08/2014.
 */
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlRootElement(name = "library")
@XmlType
public class Library {

    private ArrayList<Book> listOfBooks;
    private String name;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {

        this.name = name;
    }

    public ArrayList<Book> getListOfBooks() {
        return listOfBooks;
    }

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    public void setListOfBooks(ArrayList<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }
}
