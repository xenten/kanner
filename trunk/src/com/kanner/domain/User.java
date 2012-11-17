package com.kanner.domain;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

public class User {
	
	@PrimaryKey
	private String email;
	
	@Persistent
	private String password;
	
	@Persistent
	private String firstName;
	
	@Persistent
	private String lastName;

}