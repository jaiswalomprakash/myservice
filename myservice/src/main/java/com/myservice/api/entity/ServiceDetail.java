package com.myservice.api.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the service_details database table.
 * 
 */
@Entity
@Table(name="service_details")
@NamedQuery(name="ServiceDetail.findAll", query="SELECT s FROM ServiceDetail s")
public class ServiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="details_id")
	private Long detailsId;

	private String contact;

	@Column(name="contact_sec")
	private String contactSec;

	@Lob
	private byte[] desc;

	private String email;

	private String location;

	//bi-directional many-to-one association to Service
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="service_id")
	private Service service;

	public ServiceDetail() {
	}

	public Long getDetailsId() {
		return this.detailsId;
	}

	public void setDetailsId(Long detailsId) {
		this.detailsId = detailsId;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactSec() {
		return this.contactSec;
	}

	public void setContactSec(String contactSec) {
		this.contactSec = contactSec;
	}

	public byte[] getDesc() {
		return this.desc;
	}

	public void setDesc(byte[] desc) {
		this.desc = desc;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}