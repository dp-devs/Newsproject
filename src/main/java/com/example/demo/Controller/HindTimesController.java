package com.example.demo.Controller;

import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.example.demo.modal.News;
import com.example.demo.modal.Response;
import com.example.demo.modal.toi.Item;
import com.example.demo.modal.toi.rss;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
@PropertySource("classpath:HindustanTimesLink.properties")
public class HindTimesController {
	
	@Autowired
    Environment env;
	
	@GetMapping(value="/news/ht/{place}",produces = "application/json")
	public ArrayList getHTNews(@PathVariable("place")String place) throws Exception
	{
	
		  String val=env.getProperty("HT_"+place);
		  //System.out.println(val);
		 /* if(val!=null)
		  {*/
		  String DUMMY_URL=val;
		  HttpURLConnection connection = (HttpURLConnection) new URL(DUMMY_URL).openConnection();
		  InputStream inputStream = connection.getInputStream();
		  Scanner s = new Scanner(inputStream).useDelimiter("\\A");
		  String result = s.hasNext() ? s.next() : "";
		  JAXBContext jaxbContext = JAXBContext.newInstance(rss.class); 		  
		  Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();		  
		  File file=new File(result); 
		  StringReader reader = new StringReader(result); 
	      rss que= (rss) jaxbUnmarshaller.unmarshal(reader);		  

	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          DocumentBuilder db = dbf.newDocumentBuilder();
          Document document = db.parse(new InputSource(new StringReader(result)));
          document.getDocumentElement().normalize();
          ArrayList<News> list_news=new ArrayList<News>();
          NodeList nodeList = document.getElementsByTagName("item");
          for(int x=0; x<nodeList.getLength(); x++) {
            //  System.out.println(nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue());
             Node item=nodeList.item(x);  
             Element element=(Element)item;
             NodeList itemchild=item.getChildNodes();
             News news=new News();
  	         news.setSource("Hindustan Times");
  	         int view=(int)(Math.random()*1000);
  	         news.setViews(view+"");
            
             for(int j=0; j<itemchild.getLength(); j++) {
             
            	 Node itemchildEl=itemchild.item(j);
            	 Element itemChildElement=(Element)itemchildEl;
            	 String name=itemChildElement.getTagName();
            	
            	 if(name.equals("title"))
            	 {
            		System.out.println("we have media content" +" url is "+itemChildElement.getAttribute("url"));
            		news.setTitle(itemChildElement.getTextContent());
            	 }
            	 else if(name.equals("description"))
            	 {
            		 news.setDescription(itemChildElement.getTextContent());
            	 }
            	 else if(name.equals("media:content"))
            	 {
            		 news.setImage(itemChildElement.getAttribute("url"));
            	 }else if(name.equals("pubDate"))
            	 {
            		 String pub_date=itemChildElement.getTextContent();
            		 news.setPubDate(pub_date);
            		 String time2 = "19:00:00";
            		 

          	          SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
          	          SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
          	          Date date = new Date();
          	          String time1 =formatter.format(date);
          	         // System.out.println(time1);
          	          Date date1 = format.parse(time1);
          	          Date date2 = format.parse(time2);
          	          long differenceInMilliSeconds
          	          = Math.abs(date2.getTime() - date1.getTime());
          	          long differenceInMinutes
          	          = (differenceInMilliSeconds / (60 * 1000)) ;
          	        news.setPosted_time(differenceInMinutes+"");
         	         
            		 
            	 }
            	 
            	 
            	 //System.out.println("  "+itemChildElement.getTagName()+" "+itemChildElement.getTextContent());
             }
             
             list_news.add(news);
             
             
          }
	      
	      
	 /*    
          ArrayList<News> list_news=new ArrayList<News>();
	      List<Item> list=que.getChannel().getItem();  
	      for(Item ans:list)  
	      {
	    	  
	          News news=new News();
	          news.setDescription(ans.getDescription());
	          news.setTitle(ans.getTitle());
	          news.setPubDate(ans.getPubDate());
	          news.setImage("");
	          news.setSource("Hindustan Times");
	          int view=(int)(Math.random()*1000);
	          news.setViews(view+"");
	          
	         
	          
	          String time2 = "19:00:00";

	          SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	          SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
	          Date date = new Date();
	          String time1 =formatter.format(date);
	         // System.out.println(time1);
	          Date date1 = format.parse(time1);
	          Date date2 = format.parse(time2);
	          long differenceInMilliSeconds
	          = Math.abs(date2.getTime() - date1.getTime());
	          long differenceInMinutes
	          = (differenceInMilliSeconds / (60 * 1000)) ;
	          
	          news.setPosted_time(differenceInMinutes+"");
	          list_news.add(news);

	      }
	      
	      Response response=new Response();
	      response.setStatus("Ok");
	      response.setData(list_news);
	      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	      String json = ow.writeValueAsString(response);
		  
		 /* return json;
		  
		  
		  }
		  else
		  {
			  Response response=new Response();
		      response.setStatus("Error in City"); 
		      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		      String json = ow.writeValueAsString(response);
		      return json;
		  }*/
	
	      return list_news;
	}
	
}
