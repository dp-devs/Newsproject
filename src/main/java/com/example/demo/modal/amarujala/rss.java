/*@XmlSchema(
    namespace = "http://www.example.com/a",
    elementFormDefault = XmlNsForm.QUALIFIED,
    xmlns = {
        @XmlNs(prefix="content", namespaceURI="http://purl.org/rss/1.0/modules/content/"),
        @XmlNs(prefix="dc", namespaceURI="http://purl.org/dc/elements/1.1/"),
        @XmlNs(prefix="media", namespaceURI="http://search.yahoo.com/mrss/"),
        @XmlNs(prefix="atom", namespaceURI="https://www.w3.org/2005/Atom")
        
    }
) */ 


package com.example.demo.modal.amarujala;

import javax.xml.bind.annotation.XmlAccessType;


import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;


@XmlRootElement(name="rss",namespace = "")

public class rss {
	
	
	private Channel channel;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	

}
