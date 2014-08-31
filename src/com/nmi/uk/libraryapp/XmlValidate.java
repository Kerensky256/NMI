package com.nmi.uk.libraryapp;

/**
 * Created by Darren on 25/08/2014.
 */
import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XmlValidate {

    public static void main(String[] args) throws JAXBException {
        Library libToVal = new Library();
        System.out.println(libToVal.getClass());

        Book valBook = new Book();

        //System.out.println(Library.class.getDeclaredAnnotations()[0].toString());
        //System.out.println(Library.class.getDeclaredAnnotations()[1].toString());
        for (int i = 0; i < Library.class.getDeclaredAnnotations().length; i++) {
            System.out.println(Library.class.getDeclaredAnnotations()[i].toString());
        }

        for (int i = 0; i < Library.class.getFields().length; i++) {
            System.out.println(Library.class.getFields()[i].toString());
        }
    }

}
