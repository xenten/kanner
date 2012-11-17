package com.kanner.domain;

import java.util.List;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

public class UserClass {
	
	@PrimaryKey
	private String name;
	
	@Persistent
	private String description;
	
	@Persistent
	private List<User> userList;

}
