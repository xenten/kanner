package com.kanner.domain;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@XmlRootElement
@PersistenceCapable
public class Queue {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String id;
	
	@Persistent
	private String owner;
	
	@Persistent
	private List<Card> cardList;

	public Key getName() {
		return key;
	}

	public void setName(Key key) {
		this.key = key;
	}
	
	public String getId() {
		
		String idKey;
		
		if (key != null) {
			
			idKey = KeyFactory.keyToString(key);
			
		} else {
			
			idKey = id;
		}
		
		return idKey;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}

	public List<Card> getCardList() {
		
		if (cardList == null) {
			
			cardList = new ArrayList<Card>();
		}
		
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
