package com.kanner.domain;

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
	private String name;
	
	@Persistent
	private String descr;
	
	@Persistent
	private List<Card> cardList;
	
	@Persistent
	private List<Queue> pullQueues;
	
	@Persistent
	private List<Queue> pushQueues;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
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
		
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public String getName() {
		return name;
	}

	public void setName(String owner) {
		this.name = owner;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public List<Queue> getPullQueues() {
		return pullQueues;
	}

	public void setPullQueues(List<Queue> pullQueues) {
		this.pullQueues = pullQueues;
	}

	public List<Queue> getPushQueues() {
		return pushQueues;
	}

	public void setPushQueues(List<Queue> pushQueues) {
		this.pushQueues = pushQueues;
	}
}
