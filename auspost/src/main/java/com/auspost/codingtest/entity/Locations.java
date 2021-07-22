package com.auspost.codingtest.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
@Entity
@Table(name="LOCATIONS")
public class Locations implements Serializable { 
	public Locations(int locationId, String suburb, String postcode) {
		super();
		this.locationId = locationId;
		this.suburb = suburb;
		this.postcode = postcode;
	}
	public Locations(){}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="location_id")
	private int locationId;  
	@Column(name="suburb")
	@NotBlank(message="Suburb is required field")
    private String suburb;
	@Column(name="postcode")
	@NotBlank(message="Postcode is required field")
	private String postcode;
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", suburb=" + suburb + ", postcode=" + postcode + "]";
	}
	
	} 