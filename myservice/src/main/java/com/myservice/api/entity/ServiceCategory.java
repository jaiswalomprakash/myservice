package com.myservice.api.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the service_category database table.
 * 
 */
@Entity
@Table(name="service_category")
@NamedQuery(name="ServiceCategory.findAll", query="SELECT s FROM ServiceCategory s")
public class ServiceCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
	private int categoryId;

	private boolean active;

	@Column(name="category_name")
	private String categoryName;

	private String desc;

	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy="serviceCategory")
	private List<Service> services;

	public ServiceCategory() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Service addService(Service service) {
		getServices().add(service);
		service.setServiceCategory(this);

		return service;
	}

	public Service removeService(Service service) {
		getServices().remove(service);
		service.setServiceCategory(null);

		return service;
	}

}