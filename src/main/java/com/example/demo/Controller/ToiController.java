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

import com.example.demo.modal.News;
import com.example.demo.modal.Response;
import com.example.demo.modal.toi.Item;
import com.example.demo.modal.toi.description;
import com.example.demo.modal.toi.rss;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
@PropertySource("classpath:TOILink.properties")
public class ToiController {
	
	@Autowired
    Environment env;
	@Autowired
	HindTimesController htCont;
	
	@GetMapping(value="/news/toi/{place}",produces = "application/json")
	public String gettoiNews(@PathVariable("place")String place) throws Exception
	{
	
	  String val=env.getProperty(place);
	  System.out.println(val);
	  if(val!=null)
	  {
	  String DUMMY_URL=val;
	  HttpURLConnection connection = (HttpURLConnection) new URL(DUMMY_URL).openConnection();
	  InputStream inputStream = connection.getInputStream();
	  Scanner s = new Scanner(inputStream).useDelimiter("\\A");
	  String result = s.hasNext() ? s.next() : "";
	  //int out=inputStream.read();	
	//  System.out.println(result);
	  
	  JAXBContext jaxbContext = JAXBContext.newInstance(rss.class); 
	  
	  Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	  
	  File file=new File(result); 
	  StringReader reader = new StringReader(result); 
      rss que= (rss) jaxbUnmarshaller.unmarshal(reader);
    //  System.out.println("Items ---------------start");
      
      
      ArrayList<News> list_news=new ArrayList<News>();
      
      List<Item> list=que.getChannel().getItem();  
      for(Item ans:list)  
      {
    	  
          News news=new News();
          //news.setDescription(ans.getDescription());
          //  System.out.println("description"+ans.getDescription());
          // description desc=ans.getDescription();
          //  System.out.println("description a"+ans.getDescription().getA());
          String arr[]=ans.getDescription().split("/>");
          //System.out.println(arr[0]);
          String img_url=arr[0].substring(arr[0].indexOf("src=")+4).replace("\"","");
          System.out.println(img_url);
          //System.out.println("description "+ans.getDescription());
          //System.out.println("arr length "+arr.length);
			/*
			 * DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			 * DocumentBuilder builder = factory.newDocumentBuilder(); Document
			 * document=builder.parse(result);
			 * 
			 * Element root = document.getDocumentElement(); System.out.println("img val "
			 * +root.getAttribute("description"));
			 */
          
          if(arr.length > 1)
          {
             //System.out.println("arr "+arr[0]+" arr2 "+arr[1]);
             news.setDescription(arr[1]);          
          }
          else 
          {
        	  news.setDescription("");
          }
        	  
          news.setTitle(ans.getTitle());
          news.setPubDate(ans.getPubDate());
          news.setImage(img_url);
          news.setSource("Times Of India");
          int view=(int)(Math.random()*1000);
          news.setViews(view+"");
          
          String time2 = "19:00:00";

          SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
          SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
          Date date = new Date();	
          String time1 =formatter.format(date);
          //System.out.println(time1);
          Date date1 = format.parse(time1);
          Date date2 = format.parse(time2);
          long differenceInMilliSeconds
          = Math.abs(date2.getTime() - date1.getTime());
          long differenceInMinutes
          = (differenceInMilliSeconds / (60 * 1000)) ;
        //  System.out.println(differenceInMinutes);
          
          /*   LocalDate start_date=LocalDate.now();
             LocalDate end_date=LocalDate.parse("19:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
          
          Period diff
          = Period
                .between(start_date,
                         end_date);
          System.out.println(diff.getDays());
          
          */
       // System.out.println(" "+ans.getTitle()+"  "+"");
          news.setPosted_time(differenceInMinutes+"");
          list_news.add(news);
          
       }
      
      
      
      ArrayList<News> htnews=htCont.getHTNews(place);
      list_news.addAll(htnews);
      //System.out.println("ht news"+htnews);
      //htnews.forEach(i->System.out.println(i.getSource()));
     
     
 
      //System.out.println("inside hhhh");
	 // Serializer ser = new Persister();
      Response response=new Response();
      response.setStatus("ok"); 
      response.setData(list_news);
      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
      String json = ow.writeValueAsString(response);
     // System.out.println("response"+json);
	  
     

      return json;
	  }
	  else
	  {
		  Response response=new Response();
	      response.setStatus("Error in City"); 
	      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	      String json = ow.writeValueAsString(response);
	      return json;
	  }
	  
	}

}
