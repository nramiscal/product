package com.nramiscal.loginReg.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="packages")
public class Package {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min=1)
	private String name;
	
	@Column
	@Min(0)
	private float price;
	
	@Column
	private int available = 0;
	
	@Column
	@Min(1)
	@Max(31)
	private int startday = 1;
	
	@Column
	private Date created_at;
	
	@Column
	private Date updated_at;
	
    @ManyToMany(mappedBy = "packages")
    private List<User> users;
	
	// constructors
	
	public Package() {
		
	}
	
	public Package(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	// getters and setters

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public int available() {
		return available;
	}

	public int getStartDay() {
		return startday;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public void setStartDay(int startday) {
		this.startday = startday;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	@PrePersist
	protected void onCreate(){
	this.created_at = new Date();
	}

	@PreUpdate
	protected void onUpdate(){
	this.updated_at = new Date();
	}
	

}
