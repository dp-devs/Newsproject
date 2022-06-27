package com.example.demo.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.News;
import com.example.demo.modal.Response;
import com.example.demo.modal.amarujala.Item;
import com.example.demo.modal.amarujala.rss;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@RestController
public class NewsController {
	
	
	
	
	
	 @GetMapping("/")
	  public String hello() {
	    return "hello world!";
	  }
	
	@GetMapping(value="/news/{place}",produces = "application/json")
	public Response getNews(@PathVariable("place")String place) throws Exception
	{
		
		
	  String DUMMY_URL="https://www.amarujala.com/rss/"+place+".xml";
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
     // System.out.println("Items ---------------start");
      
      
      ArrayList<News> list_news=new ArrayList<News>();
      
      List<Item> list=que.getChannel().getItem();  
      for(Item ans:list)  
      {
    	  
          News news=new News();
          news.setDescription(ans.getDescription());
          news.setTitle(ans.getTitle());
          news.setPubDate(ans.getPubDate());
          news.setImage("");
          news.setViews(Math.random()*1000+"");
          
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
          //System.out.println(differenceInMinutes);
          
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
 
     // System.out.println("inside hhhh");
	 // Serializer ser = new Persister();
      Response response=new Response();
      response.setStatus("ok"); 
      response.setData(list_news);
		return response;
	}
	
	
	
	

}
