package com.example.demo.modal.toi;

import javax.xml.bind.annotation.XmlElement;

public class description {
	
	private String a;
	private String img;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getimg() {
		return img;
	}

	@XmlElement(name="img")
	public void setimg(String img) {
		this.img = img;
	}
	
	

}
