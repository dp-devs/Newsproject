package com.example.demo.modal.amarujala;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class Channel {
	
	private String link;
	private String title;
	private String description;
	
	private String atomlink;
	private image image;
	private String language;
	private String lastBuildDate;
	private ArrayList<Item> Item;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAtomlink() {
		return atomlink;
	}
	//@XmlElement(name ="atom:link ")
	public void setAtomlink(String atomlink) {
		this.atomlink = atomlink;
	}
	public image getImage() {
		return image;
	}
	public void setImage(image image) {
		this.image = image;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLastBuildDate() {
		return lastBuildDate;
	}
	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	public ArrayList<Item> getItem() {
		return Item;
	}
	public void setItem(ArrayList<Item> item) {
		Item = item;
	}
	
	

}
