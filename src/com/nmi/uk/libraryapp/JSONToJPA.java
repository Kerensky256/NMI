package com.nmi.uk.dbtest.main;

/*
 import java.util.*;
 import javax.ws.rs.core.Application;
 import org.eclipse.persise
 /**
 * Created by Darren on 27/08/2014.
 */

/*
 public class JSONToJPA {
 }
 */
import java.io.FileNotFoundException;
import javax.xml.bind.*;
import javax.xml.stream.XMLStreamReader;
//import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.Iterator;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

public class JSONToJPA {

    private String filename;
    private JSONArray bookList;

    public JSONToJPA(String filename) {
        this.filename = filename;
    }

    public JSONArray parseJson() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(new FileReader(filename));

            String name = (String) jsonObj.get("name");
            JSONArray bookList = (JSONArray) jsonObj.get("books");

            System.out.println("Library: " + name);
            Iterator i = bookList.iterator();
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                System.out.println("title " + innerObj.get("name")
                        + " author " + innerObj.get("author") + " category " + innerObj.get("category"));
            }

            this.bookList = bookList;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSONToJPA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSONToJPA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(JSONToJPA.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bookList;
    }
}
