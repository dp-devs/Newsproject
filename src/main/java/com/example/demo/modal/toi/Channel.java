package com.example.demo.modal.toi;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class Channel {
	
	private String title;
	private String link;
	private String description;
	private String language;
	private String lastBuildDate;
	private String atomlink;
	private String copyright;
	private String docs;
	private image image;
	private ArrayList<Item> Item;
	
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getDocs() {
		return docs;
	}
	public void setDocs(String docs) {
		this.docs = docs;
	}
	
	
	
	
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
