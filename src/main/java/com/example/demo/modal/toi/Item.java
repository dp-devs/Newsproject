package com.example.demo.modal.toi;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Item {
	
	private String title;
	private String description;
	private String link;
	private String guid;
	private String pubDate;
	
	
	private String creator;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCreator() {
		return creator;
	}

	//@XmlElement(name="dc:creator")
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
	

}
