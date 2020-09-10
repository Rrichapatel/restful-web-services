package com.learning.rest.webservice.restfulwebservices.helloworld.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//annotation used for swagger documentation enhancement
@ApiModel(description = "this description about user model will be shown in swagger documentaion for end user.")
@Entity //JPA enabled
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 2, message="Name should have more then 2 characters")
	@ApiModelProperty(notes = "Swagger Note: Name should have atleast 2 characters. ")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "Birthdate should be before then today's date. ")
	private Date dob;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	public User() {
		super();
	}

	public User(Integer id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		//return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
		return String.format("User [id=%s, name=%s, dob=%dob]", id, name, dob);
	}

}
