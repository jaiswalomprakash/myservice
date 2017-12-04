package com.myservice.api.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_id")
	private Long serviceId;

	private boolean active;

	private String desc;

	private String name;

	//bi-directional many-to-one association to ServiceCategory
	@ManyToOne
	@JoinColumn(name="service_type")
	private ServiceCategory serviceCategory;

	//bi-directional many-to-one association to ServiceDetail
	@OneToMany(mappedBy="service")
	private List<ServiceDetail> serviceDetails;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="services")
	private List<User> users;

	public Service() {
	}

	public Long getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ServiceCategory getServiceCategory() {
		return this.serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public List<ServiceDetail> getServiceDetails() {
		return this.serviceDetails;
	}

	public void setServiceDetails(List<ServiceDetail> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public ServiceDetail addServiceDetail(ServiceDetail serviceDetail) {
		getServiceDetails().add(serviceDetail);
		serviceDetail.setService(this);

		return serviceDetail;
	}

	public ServiceDetail removeServiceDetail(ServiceDetail serviceDetail) {
		getServiceDetails().remove(serviceDetail);
		serviceDetail.setService(null);

		return serviceDetail;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}