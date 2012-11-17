package com.kanner.domain;

import java.text.ParseException;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@PersistenceCapable
public class Card {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private int version;
	
	@Persistent
	private String title;
	
	@Persistent
	private String description;
	
	@Persistent
	private String createdOn;
	
	@Persistent
	private String owner;
	
	@Persistent
	private String requestedReleaseDate;
	
	@Persistent
	private String requirements;
	

	public int getVersion() {
		
		return version;
	}

	public void setVersion(int version) {
		
		this.version = version;
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

	public String getCreatedOn() {
		
		return createdOn;
	}

	public void setCreatedOn(String createdOn) throws ParseException {
		
		this.createdOn = createdOn;
	}

	public String getOwner() {
		
		return owner;
	}

	public void setOwner(String owner) {
		
		this.owner = owner;
	}

	public Long getId() {
		
		return id;
	}
	
	public void setId(Long id) {
		
		this.id = id;
	}

	public void setVersion(String version) {
		
		this.version = Integer.parseInt(version);
	}

	public String getRequestedReleaseDate() {
		
		return requestedReleaseDate;
	}

	public void setRequestedReleaseDate(String requestedReleaseDate) {
		
		this.requestedReleaseDate = requestedReleaseDate;
	}

	public String getRequirements() {
		
		return requirements;
	}

	public void setRequirements(String requirements) {
		
		this.requirements = requirements;
	}	

}