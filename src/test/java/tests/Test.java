package tests;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;

public class Test {
	 private static Gson gson = new Gson();
	 
	  public static void main(String[] args) throws IOException
	  {
	    parseJson();
	 
	    saveJson();
	 
	  }
	 
	  public static void parseJson() throws IOException
	  {
	    String jsonStr = "{ \"author\": \"Steve Jin\", \"title\" : \"vSphere SDK\", \"obj\" : {\"objint\" : {}} }";
	    Object obj = gson.fromJson(jsonStr, Object.class);
	 
	    System.out.println("obj type: " + obj.getClass().toString()); // com.google.gson.internal.LinkedTreeMap
	    System.out.println("obj: " + obj);
	 
	    Map m = gson.fromJson(jsonStr, Map.class);
	    System.out.println("m: " + m.size());  // 3
	 
	    for(Object key: m.keySet())
	    {
	      System.out.println("key:" + key);
	    }
	 
	    Book book = gson.fromJson(jsonStr, Book.class);
	    System.out.println("book: " + book);
	    System.out.println("book.author: " + book.author);
	    System.out.println("book.obj class: " + book.obj.getClass()); //com.google.gson.internal.LinkedTreeMap
	    System.out.println("book.obj: " + book.obj);
	  }
	 
	  public static void saveJson() throws IOException
	  {
	    Book book = new Book();
	    book.author = "Steve Jin";
	    book.title = "VMware vSphere and VI SDK";
	 
	    String bookJson = gson.toJson(book);
	    System.out.println("bookJson: " + bookJson);
	  }
	 
	  
}

class Book
{
  public String author;
  public String title;
  public Map obj;
}
